package tech.jianka.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.storage.StorageManager;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import tech.jianka.data.ItemData;


public class FileUtil {

    /****
     * 计算文件大小
     *
     * @param length
     * @return
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

    public static String FormetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
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

//    public static int getFileTypeImageId(Context mContext, String fileName) {
//        int id;
//        if (checkSuffix(fileName, new String[]{"mp3"})) {
//            id = R.drawable.rc_ad_list_audio_icon;
//
//        } else if (checkSuffix(fileName, new String[]{"wmv", "rmvb", "avi", "mp4"})) {
//            id = R.drawable.rc_ad_list_video_icon;
//        } else if (checkSuffix(fileName, new String[]{"wav", "aac", "amr"})) {
//            id = R.drawable.rc_ad_list_video_icon;
//        }
////        if (checkSuffix(fileName, mContext.getResources().getStringArray(R.array.rc_file_file_suffix)))
////            id = R.drawable.rc_ad_list_file_icon;
////        else if (checkSuffix(fileName, mContext.getResources().getStringArray(R.array.rc_video_file_suffix)))
////            id = R.drawable.rc_ad_list_video_icon;
////        else if (checkSuffix(fileName, mContext.getResources().getStringArray(R.array.rc_audio_file_suffix)))
////            id = R.drawable.rc_ad_list_audio_icon;
//        else
//            id = R.drawable.rc_ad_list_other_icon;
//        return id;
//    }

    public static boolean checkSuffix(String fileName,
                                      String[] fileSuffix) {
        for (String suffix : fileSuffix) {
            if (fileName != null) {
                if (fileName.toLowerCase().endsWith(suffix)) {
                    return true;
                }
            }
        }
        return false;
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


    public static List<ItemData> getFilesInfo(List<String> fileDir, Context mContext) {
        List<ItemData> mlist = new ArrayList<>();
        for (int i = 0; i < fileDir.size(); i++) {
            if (new File(fileDir.get(i)).exists()) {
                mlist = FilesInfo(new File(fileDir.get(i)), mContext);
            }
        }
        return mlist;
    }

    private static List<ItemData> FilesInfo(File fileDir, Context mContext) {
        List<ItemData> videoFilesInfo = new ArrayList<>();
        File[] listFiles = fileFilter(fileDir);
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isDirectory()) {
                    FilesInfo(file, mContext);
                } else {
                    ItemData ItemData = getItemInfoFromFile(file);
                    videoFilesInfo.add(ItemData);
                }
            }
        }
        return videoFilesInfo;
    }

    public static List<ItemData> getItemInfosFromFileArray(File[] files) {
        List<ItemData> itemDatas = new ArrayList<>();
        for (File file : files) {
            ItemData ItemData = getItemInfoFromFile(file);
            itemDatas.add(ItemData);
        }
        Collections.sort(itemDatas, new FileNameComparator());
        return itemDatas;
    }

    /**
     * 根据文件名进行比较排序
     */
    public static class FileNameComparator implements Comparator<ItemData> {
        protected final static int
                FIRST = -1,
                SECOND = 1;

        @Override
        public int compare(ItemData lhs, ItemData rhs) {
            if (lhs.isDirectory() || rhs.isDirectory()) {
                if (lhs.isDirectory() == rhs.isDirectory())
                    return lhs.getFileName().compareToIgnoreCase(rhs.getFileName());
                else if (lhs.isDirectory()) return FIRST;
                else return SECOND;
            }
            return lhs.getFileName().compareToIgnoreCase(rhs.getFileName());
        }
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

    public static List<ItemData> queryItemInfo(Context context, List<Uri> mlist) {
        List<ItemData> itemDatas = new ArrayList<>();

        for (int i = 0; i < mlist.size(); i++) {
            String[] projection = new String[]{
                    MediaStore.Files.FileColumns._ID,
                    MediaStore.Files.FileColumns.DATA,
                    MediaStore.Files.FileColumns.DISPLAY_NAME,
                    MediaStore.Files.FileColumns.DATE_MODIFIED
            };
            Cursor cursor = context.getContentResolver().query(
                    mlist.get(i),
                    projection, null,
                    null, projection[2] + " DESC");

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    int dataindex = cursor
                            .getColumnIndex(MediaStore.Files.FileColumns.DATA);
                    int nameindex = cursor
                            .getColumnIndex(MediaStore.Files.FileColumns.DISPLAY_NAME);
                    int timeindex = cursor
                            .getColumnIndex(MediaStore.Files.FileColumns.DATE_MODIFIED);
                    do {
                        ItemData ItemData = new ItemData();
                        String path = cursor.getString(dataindex);
                        String name = cursor.getString(nameindex);
                        String time = cursor.getString(timeindex);
                        ItemData.setFileSize(new File(path).length());
                        ItemData.setFilePath(path);
                        ItemData.setFileName(name);
                        ItemData.setLastModifiedTime(time);
//                        ItemData ItemData = getImageFolder(path, itemDatas);
//                        ItemData.getImages().add(ItemData);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
        }
        return itemDatas;

    }

    public static List<ItemData> queryFilerInfo(Context context, List<Uri> mlist, String selection, String[] selectionArgs) {
        List<ItemData> itemDatas = new ArrayList<>();
        for (int i = 0; i < mlist.size(); i++) {
            String[] projection = new String[]{
                    MediaStore.Files.FileColumns._ID,
                    MediaStore.Files.FileColumns.DATA,
                    MediaStore.Files.FileColumns.TITLE,
                    MediaStore.Files.FileColumns.DATE_MODIFIED
            };
            Cursor cursor = context.getContentResolver().query(
                    mlist.get(i),
                    projection, selection,
                    selectionArgs, projection[2] + " DESC");

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    int dataindex = cursor
                            .getColumnIndex(MediaStore.Files.FileColumns.DATA);
                    int nameindex = cursor
                            .getColumnIndex(MediaStore.Files.FileColumns.TITLE);
                    int timeindex = cursor
                            .getColumnIndex(MediaStore.Files.FileColumns.DATE_MODIFIED);
                    do {
                        ItemData ItemData = new ItemData();
                        String path = cursor.getString(dataindex);
                        String name = cursor.getString(nameindex);
                        String time = cursor.getString(timeindex);
                        ItemData.setFileSize(new File(path).length());
                        ItemData.setFilePath(path);
                        ItemData.setFileName(name);
                        ItemData.setLastModifiedTime(time);
                        itemDatas.add(ItemData);

                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
        }
        return itemDatas;

    }

    public static ItemData getImageFolder(String path, List<ItemData> imageFolders) {
        File imageFile = new File(path);
        File folderFile = imageFile.getParentFile();

        for (ItemData folder : imageFolders) {
            if (folder.getLastModifiedTime().equals(folderFile.getName())) {
                return folder;
            }
        }
        ItemData newFolder = new ItemData();
        newFolder.setFileName(folderFile.getName());
        newFolder.setFilePath(folderFile.getAbsolutePath());
        imageFolders.add(newFolder);
        return newFolder;
    }
}
