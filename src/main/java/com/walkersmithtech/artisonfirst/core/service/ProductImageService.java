package com.walkersmithtech.artisonfirst.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.constant.ErrorCode;
import com.walkersmithtech.artisonfirst.constant.RelationshipType;
import com.walkersmithtech.artisonfirst.core.ServiceException;
import com.walkersmithtech.artisonfirst.core.builder.ProductBuilder;
import com.walkersmithtech.artisonfirst.data.model.dto.ImageDto;
import com.walkersmithtech.artisonfirst.data.model.dto.ProductDto;
import com.walkersmithtech.artisonfirst.data.model.object.Product;

@Service
public class ProductImageService
{
	@Autowired
	private FileManagerSerivce fileService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductBuilder productBuilder;
	
	public ProductDto addAndSetProductLogo( ImageDto auth, String productUid, byte[] file ) throws ServiceException
	{
		Product product = productService.getProductByUid( productUid );
		if ( product != null )
		{
			auth = addProductImage( auth, productUid, file );
			auth = setProductImage( auth, productUid, file );
			product.setImageUri( "/images/" + auth.getUid() );
			productService.updateProduct( product );

			ProductDto dto = new ProductDto();
			dto.setAccount( auth.getAccount() );
			dto.setProduct( product );
			dto = productBuilder.getProductByUid( dto );
			return dto;
		}
		throw ErrorCode.PRODUCT_NOT_FOUND.exception;
	}

	public ImageDto addProductImage( ImageDto auth, String productUid, byte[] file ) throws ServiceException
	{
		auth = ( ImageDto ) fileService.createFileRecord( file, auth );
		auth = setProductImage( auth, productUid, file );
		auth = ( ImageDto ) fileService.createFileRelation( productUid, RelationshipType.PRODUCT_IMAGE.name(), auth );
		return auth;
	}

	public ImageDto setProductImage( ImageDto auth, String productUid, byte[] file ) throws ServiceException
	{
		auth = ( ImageDto ) fileService.createFileRecord( file, auth );
		fileService.deleteFileRelationByObjectAndRole( productUid, RelationshipType.PRODUCT_IMAGE.name() );
		auth = ( ImageDto ) fileService.createFileRelation( productUid, RelationshipType.PRODUCT_IMAGE.name(), auth );
		return auth;
	}
}
