package com.pascal7.ingre_api_mono.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tx_image")
public class ImageEntity {

    @Id
    @GeneratedValue(generator = "tx_image_uuid")
    @GenericGenerator(name = "tx_image_uuid", strategy = "uuid")
    private String id;
    private String sourceId;
    private String type;
    private byte[] image;

    public ImageEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
