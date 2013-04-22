package com.gulf.web.controller;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.UploadAdaptor;

import com.gulf.service.image.ImageService;

@IocBean
@InjectName
public class PhotoController {
    @Inject
    private ImageService imageService;

    @At("/upload")
    @AdaptBy(type = UploadAdaptor.class, args = {"${app.root}/WEB-INF/tmp"})
    @Ok("raw")
    public String uploadPhoto(@Param("photo") File f) throws IOException, Exception {
        if (f != null && f.length() > 0) {
            String url = imageService.saveImage(FileUtils.readFileToByteArray(f));
            return url;
        }
        else {
            return null;
        }
    }
}
