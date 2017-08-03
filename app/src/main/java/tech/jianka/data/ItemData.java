package tech.jianka.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Richa on 2017/7/31.
 */

public class ItemData implements Parcelable {
    private String fileName;
    private String filePath;
    private long fileSize;
    private boolean isDirectory;
    private String createdTime;
    private String lastModifiedTime;
    private String suffix;
    private boolean isCheck = false;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(String lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    protected ItemData(Parcel in) {
    }
    public ItemData() {

    }

    public ItemData(String fileName, String filePath, long fileSize, boolean isDirectory, String createdTime, String lastModifiedTime, String suffix, boolean isCheck) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.isDirectory = isDirectory;
        this.createdTime = createdTime;
        this.lastModifiedTime = lastModifiedTime;
        this.suffix = suffix;
        this.isCheck = isCheck;
    }

    public static final Creator<ItemData> CREATOR = new Creator<ItemData>() {
        @Override
        public ItemData createFromParcel(Parcel in) {
            return new ItemData(in);
        }

        @Override
        public ItemData[] newArray(int size) {
            return new ItemData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fileName);
        dest.writeString(this.filePath);
        // TODO: 2017/7/31
    }
}
