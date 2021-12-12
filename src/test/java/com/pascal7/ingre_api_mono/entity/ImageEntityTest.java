package com.pascal7.ingre_api_mono.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImageEntityTest {

    @Test
    void getId() {
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setId("02020");
        assertNotNull(imageEntity.getId());
    }

    @Test
    void setId() {
        ImageEntity imageEntity = new ImageEntity();
        String id = "383534";
        imageEntity.setId(id);
        assertEquals(id, imageEntity.getId());
    }

    @Test
    void getSourceId() {
        ImageEntity imageEntity = new ImageEntity();
        String sourceId = "2keri3e";
        imageEntity.setSourceId(sourceId);
        assertNotNull(imageEntity.getSourceId());
    }

    @Test
    void setSourceId() {
        ImageEntity imageEntity = new ImageEntity();
        String sourceId = "2keri3e";
        imageEntity.setSourceId(sourceId);
        assertEquals(sourceId, imageEntity.getSourceId());
    }

    @Test
    void getImage() {
        ImageEntity imageEntity = new ImageEntity();
        byte[] image = new byte[]{1,2,3};
        imageEntity.setImage(image);
        assertNotNull(imageEntity.getImage());
    }

    @Test
    void setImage() {
        ImageEntity imageEntity = new ImageEntity();
        byte[] image = new byte[]{1,2,3};
        imageEntity.setImage(image);
        assertEquals(image, imageEntity.getImage());
    }

    @Test
    void getType() {
        ImageEntity imageEntity = new ImageEntity();
        String type = "mercedes";
        imageEntity.setType(type);
        assertNotNull(imageEntity.getType());
    }

    @Test
    void setType() {
        ImageEntity imageEntity = new ImageEntity();
        String type = "mercedes";
        imageEntity.setType(type);
        assertEquals(type, imageEntity.getType());
    }
}