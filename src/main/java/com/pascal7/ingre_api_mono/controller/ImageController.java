package com.pascal7.ingre_api_mono.controller;

import com.pascal7.ingre_api_mono.entity.ImageEntity;
import com.pascal7.ingre_api_mono.service.ImageEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ImageController {

    @Autowired
    ImageEntityService imageEntityService;

    @GetMapping("/api/product/image/{id}")
    public void getImage(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageEntity imageEntity = imageEntityService.getBySourceId(id);
        response.setContentType(imageEntity.getType());
        response.getOutputStream().write(imageEntity.getImage());
        response.getOutputStream().close();
    }
}
