package com.org.proto.catalog.service.impl;

import com.org.proto.catalog.client.AwsS3Client;
import com.org.proto.catalog.model.CatalogItem;
import com.org.proto.catalog.repository.CatalogItemRepository;
import com.org.proto.catalog.service.CatalogService;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CatalogItemRepository repository;

    @Autowired
    private AwsS3Client client;

    @Override
    public ResponseEntity addItem(final CatalogItem catalogItem) {
        return ResponseEntity.ok().body(repository.save(catalogItem));
    }

    @Override
    public ResponseEntity addImage(final Long id, final MultipartFile multipartFile) {
      //  var response = repository.findById(id).get();
       // var image = client.uploadFile(String.valueOf(response.getId()), multipartFile);
       // response.setImageUrl(image);
    	CatalogItem c= new CatalogItem();
        return ResponseEntity.ok().body(repository.save(c));
    }

    @Override
    public ResponseEntity getItem() {
       // var response = repository.findAll();
        CatalogItem response= new CatalogItem();
        return  ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity getItem(final Long id) {
    	Optional<CatalogItem> response = repository.findById(id);
        return response.isPresent() ? ResponseEntity.ok(response.get()) : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity updateItem(final CatalogItem catalogItem) {

    	Optional<CatalogItem> item = repository.findById(catalogItem.getId());
        if(!item.isPresent()) {
            return ResponseEntity.badRequest().body("Id is invalid");
        }
        return ResponseEntity.ok().body(repository.save(catalogItem));
    }

    @Override
    public ResponseEntity updateImage(final Long id, final MultipartFile multipartFile) {
    	CatalogItem catalogItem = repository.findById(id).get();
        if(!multipartFile.isEmpty()) {
//            client.deleteFile(String.valueOf(catalogItem.getId()));
//            client.uploadFile(String.valueOf(catalogItem.getId()), multipartFile);
        }
        return ResponseEntity.ok().body(repository.save(catalogItem));
    }

    @Override
    public void removeItem() {
//        if(client.deleteFile()) {
//            repository.deleteAll();
//        }
    }

    @Override
    public void removeItem(final Long id) {
//        if(client.deleteFile(String.valueOf(id))) {
//            repository.deleteById(id);
//        }
    }

	@Override
	public List<CatalogItem> getItemAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}
}
