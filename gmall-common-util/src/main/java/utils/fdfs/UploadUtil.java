package utils.fdfs;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

public class UploadUtil {


    public static String uploadImage(MultipartFile file)  {

        String url = "http://119.23.187.248";

        String path = UploadUtil.class.getResource("/tracker.conf").getPath();
        try {
            ClientGlobal.init(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

        TrackerClient trackerClient = new TrackerClient();

        TrackerServer trackerServer = null;

        try {
            trackerServer = trackerClient.getConnection();
            StorageClient storageClient = new StorageClient(trackerServer,null);
            byte[] bytes = file.getBytes();
            String originalFilename = file.getOriginalFilename();
            int index = originalFilename.lastIndexOf(".");
            String extName = originalFilename.substring(index + 1);
            String[] jpgs = storageClient.upload_file(bytes,extName,null);

            if(ObjectUtils.isEmpty(jpgs)) {
                return null;
            }
            for (String jpg : jpgs) {
                url += "/" + jpg;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return url;
    }
}
