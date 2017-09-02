package com.walkersmithtech.artisonfirst.core.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.walkersmithtech.artisonfirst.constant.DataType;
import com.walkersmithtech.artisonfirst.constant.ErrorCode;
import com.walkersmithtech.artisonfirst.constant.IndexType;
import com.walkersmithtech.artisonfirst.core.BaseObjectService;
import com.walkersmithtech.artisonfirst.core.ServiceException;
import com.walkersmithtech.artisonfirst.data.model.object.Location;

@Service
public class LocationService extends BaseObjectService<Location>
{

	public LocationService()
	{
		this.dataType = DataType.LOCATION;
		this.modelClass = Location.class;
	}

	public List<Location> createLocations( List<Location> locations ) throws ServiceException
	{
		if ( locations != null && locations.size() > 0 )
		{
			for ( Location location : locations )
			{
				location = createLocation( location );
			}
		}
		return locations;
	}

	public Location createLocation( Location location ) throws ServiceException
	{
		validate( location );
		return createModel( location );
	}

	public List<Location> updateLocations( List<Location> locations ) throws ServiceException
	{
		if ( locations != null && locations.size() > 0 )
		{
			for ( Location location : locations )
			{
				if ( location.getUid() == null || location.getUid().isEmpty() )
				{
					location = createLocation( location );
				}
				else
				{
					location = updateLocation( location );
				}
			}
		}
		return locations;
	}

	public Location updateLocation( Location location ) throws ServiceException
	{
		validate( location );
		Location match = getModelByUid( location.getUid() );
		if ( match == null )
		{
			throw ErrorCode.LOCATION_NOT_FOUND.exception;
		}
		return updateModel( location );
	}

	@Override
	protected void createIndex( Location model )
	{
		indexRepo.deleteByUid( model.getUid() );
		saveIndexData( model.getUid(), IndexType.CITY, model.getCity() );
		saveIndexData( model.getUid(), IndexType.STATE, model.getState() );
		saveIndexData( model.getUid(), IndexType.ZIP, model.getZip() );
		saveIndexData( model.getUid(), IndexType.COUNTRY, model.getCountry() );
	}

	@Override
	protected void validate( Location location ) throws ServiceException
	{
		if ( location == null )
		{
			throw ErrorCode.LOCATION_RECORD_NULL.exception;
		}

		if ( location.getAddressName() == null )
		{
			throw ErrorCode.LOCATION_NAME_MISSING.exception;
		}

		if ( location.getAddress1() == null || location.getAddress1().isEmpty() )
		{
			throw ErrorCode.LOCATION_NAME_MISSING.exception;
		}

		if ( location.getCity() == null || location.getCity().isEmpty() )
		{
			throw ErrorCode.LOCATION_NAME_MISSING.exception;
		}

	}
}
