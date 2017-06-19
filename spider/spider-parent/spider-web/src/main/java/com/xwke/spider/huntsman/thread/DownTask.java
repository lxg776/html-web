package com.xwke.spider.huntsman.thread;

import com.xwke.spider.huntsman.listener.DownImgListener;
import com.xwke.spider.huntsman.util.DownloadPictureUtil;



public class DownTask implements Runnable{  
        String url;  
        String dirPath;
        String fileName;
        DownImgListener listener;
        public DownTask(String url,String dirPath,String fileName,DownImgListener listener){  
                this.url = url;
                this.dirPath=dirPath;
                this.fileName=fileName;
              
        }  
        @Override  
        public void run() {
          	DownloadPictureUtil.downloadPicture(url, dirPath, fileName,listener);
        }  
          
} 