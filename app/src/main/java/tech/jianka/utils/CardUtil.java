package tech.jianka.utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import tech.jianka.data.Item;
import tech.jianka.data.ItemData;

/**
 * Created by Richa on 2017/7/31.
 */

public class CardUtil {
    /****
     * 计算文件大小
     *
     * @param length
     * @return 文件大小
     */
    public static String getFileSzie(Long length) {
        if (length >= 1048576) {
            return (length / 1048576) + "MB";
        } else if (length >= 1024) {
            return (length / 1024) + "KB";
        } else if (length < 1024) {
            return length + "B";
        } else {
            return "0KB";
        }
    }

    /**
     * 字符串时间戳转时间格式
     *
     * @param timeStamp
     * @return
     */
    public static String getStrTime(String timeStamp) {
        String timeString = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 hh:mm");
        DateFormat dateFormat = SimpleDateFormat.getDateTimeInstance(SimpleDateFormat.DATE_FIELD, SimpleDateFormat.TIMEZONE_FIELD, Locale.CHINA);
        long l = Long.valueOf(timeStamp) * 1000;
        timeString = sdf.format(new Date(l));
        return timeString;
    }

    /**
     * 读取文件的最后修改时间的方法
     */
    public static String getFileLastModifiedTime(File f) {
        Calendar cal = Calendar.getInstance();
        long time = f.lastModified();
        SimpleDateFormat formatter = new
                SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        cal.setTimeInMillis(time);
        return formatter.format(cal.getTime());
    }

    /**
     * 获取扩展内存的路径
     *
     * @param mContext
     * @return
     */
    @Nullable
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String getStoragePath(Context mContext) {
        StorageManager mStorageManager = (StorageManager) mContext.getSystemService(Context.STORAGE_SERVICE);
        Class<?> storageVolumeClazz = null;
        try {
            storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
            Method getVolumeList = mStorageManager.getClass().getMethod("getVolumeList");
            Method getPath = storageVolumeClazz.getMethod("getPath");
            Method isRemovable = storageVolumeClazz.getMethod("isRemovable");
            Object result = getVolumeList.invoke(mStorageManager);
            final int length = Array.getLength(result);
            for (int i = 0; i < length; i++) {
                Object storageVolumeElement = Array.get(result, i);
                String path = (String) getPath.invoke(storageVolumeElement);
                boolean removable = (Boolean) isRemovable.invoke(storageVolumeElement);
                if (removable) {
                    return path;
                }
            }
        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取sdcard绝对物理路径
     */
    @Nullable
    public static String getSDCardPath() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            return null;
        }
    }

    /**
     * 获取sdcard某文件夹物理路径
     */
    @Nullable
    public static String getSpecifiedSDPath(String path) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory().getAbsolutePath() + path;
        } else {
            return null;
        }
    }


    /**
     * 文件过滤,将手机中隐藏的文件给过滤掉
     */
    public static File[] fileFilter(File file) {
        File[] files = file.listFiles(new FileFilter() {

            @Override
            public boolean accept(File pathname) {
                return !pathname.isHidden();
            }
        });
        return files;
    }

    /**
     * @param groupDir
     * @return
     */
    @Nullable
    public static List<Item> getChildItems(String groupDir) {
        File group = new File(groupDir);
        ArrayList<Item> childItems;
        File[] children = fileFilter(group);
        if (children != null && children.length != 0) {
            childItems = getCardsFromFileArray(children);
            return childItems;
        }else return null;
    }

    @Nullable
    private static ArrayList<Item> getCardsFromFileArray(File[] children) {
        ArrayList<Item> items = new ArrayList<>();
        for (File child : children) {
            Item item = getCardFromFile(child);
            items.add(item);
        }
//        做一个排序
//        Collections.sort(items,new FileNameComparator());
        return null;
    }

    private static Item getCardFromFile(File child) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        Item item = new Item();
        try {
            fis = new FileInputStream(child.toString());
            ois = new ObjectInputStream(fis);
            item = (Item) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    public static ItemData getItemInfoFromFile(File file) {
        ItemData ItemData = new ItemData();
        ItemData.setFileName(file.getName());
        ItemData.setFilePath(file.getPath());
        ItemData.setFileSize(file.length());
        ItemData.setDirectory(file.isDirectory());
        ItemData.setLastModifiedTime(FileUtil.getFileLastModifiedTime(file));
        int lastDotIndex = file.getName().lastIndexOf(".");
        if (lastDotIndex > 0) {
            String fileSuffix = file.getName().substring(lastDotIndex + 1);
            ItemData.setSuffix(fileSuffix);
        }
        return ItemData;
    }

}
