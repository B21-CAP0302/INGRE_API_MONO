package com.pascal7.ingre_api_mono.service;

import com.pascal7.ingre_api_mono.entity.ImageEntity;
import com.pascal7.ingre_api_mono.repository.ImageEntityRepository;
import com.pascal7.ingre_api_mono.utils.BankString;
import com.pascal7.ingre_api_mono.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.util.List;

@Service
public class ImageEntityService implements CRUDServiceTemplate<ImageEntity>{

    @Autowired
    ImageEntityRepository imageEntityRepository;

    @Autowired
    Helper helper;

    @Override
    public ImageEntity create(ImageEntity imageEntity) {
        helper.validateIdIsNull(imageEntity.getId());
        return imageEntityRepository.save(imageEntity);
    }

    @Override
    public ImageEntity getById(String id) {
        validateIdIsExist(id);
        return imageEntityRepository.getById(id);
    }

    public ImageEntity getBySourceId(String id){
        validateSourceIdIsExist(id);
        return imageEntityRepository.findBySourceId(id).get();
    }

    private void validateIdIsExist(String id) {
        if(!imageEntityRepository.findById(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BankString.idDidNotExist);
        }
    }

    private void validateSourceIdIsExist(String id) {
        if(!imageEntityRepository.findBySourceId(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BankString.idDidNotExist);
        }
    }

    @Override
    public ImageEntity update(ImageEntity imageEntity) {
        validateIdIsExist(imageEntity.getId());
        return getBySourceId(imageEntity.getSourceId());
    }

    @Override
    public List<ImageEntity> getAll() {
        return imageEntityRepository.findAll();
    }

    @Override
    public void delete(String id) {
        validateIdIsExist(id);
        imageEntityRepository.delete(getById(id));
    }

    public void addMultipartFile(String id, MultipartFile multipartFile) throws IOException {
        String originalName = multipartFile.getOriginalFilename();
        helper.validateFileExtension(FilenameUtils.getExtension(originalName));
        ImageEntity imageEntity;
        imageEntity = getImageEntity(id);
        imageEntity.setSourceId(id);
        imageEntity.setImage(multipartFile.getBytes());
        imageEntity.setType("image/" + FilenameUtils.getExtension(originalName));
        addImageEntityToDatabase(imageEntity);
    }

    private void addImageEntityToDatabase(ImageEntity imageEntity) {
        if(imageEntity.getId() != null){
            update(imageEntity);
        } else {
            create(imageEntity);
        }
    }

    private ImageEntity getImageEntity(String id) {
        ImageEntity imageEntity;
        if(imageEntityRepository.findBySourceId(id).isPresent()){
            imageEntity = imageEntityRepository.findBySourceId(id).get();
        } else {
            imageEntity = new ImageEntity();
        }
        return imageEntity;
    }
}
