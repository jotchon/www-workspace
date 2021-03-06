/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.www.marketing.events.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.www.marketing.events.model.MarketingEventAnnouncement;
import com.liferay.osb.www.marketing.events.service.MarketingEventAnnouncementLocalService;
import com.liferay.osb.www.marketing.events.service.persistence.MarketingEventAnnouncementPersistence;
import com.liferay.osb.www.marketing.events.service.persistence.MarketingEventPersistence;
import com.liferay.osb.www.marketing.events.service.persistence.MarketingEventSessionFinder;
import com.liferay.osb.www.marketing.events.service.persistence.MarketingEventSessionPersistence;
import com.liferay.osb.www.marketing.events.service.persistence.MarketingEventSessionRoomPersistence;
import com.liferay.osb.www.marketing.events.service.persistence.MarketingEventSponsorLevelPersistence;
import com.liferay.osb.www.marketing.events.service.persistence.MarketingEventSponsorPersistence;
import com.liferay.osb.www.marketing.events.service.persistence.MarketingEventUserFinder;
import com.liferay.osb.www.marketing.events.service.persistence.MarketingEventUserPersistence;
import com.liferay.osb.www.marketing.events.service.persistence.SocialLinkPersistence;
import com.liferay.osb.www.marketing.events.service.persistence.SocialLinkTypePersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the marketing event announcement local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.osb.www.marketing.events.service.impl.MarketingEventAnnouncementLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.www.marketing.events.service.impl.MarketingEventAnnouncementLocalServiceImpl
 * @see com.liferay.osb.www.marketing.events.service.MarketingEventAnnouncementLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class MarketingEventAnnouncementLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements MarketingEventAnnouncementLocalService, IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.osb.www.marketing.events.service.MarketingEventAnnouncementLocalServiceUtil} to access the marketing event announcement local service.
	 */

	/**
	 * Adds the marketing event announcement to the database. Also notifies the appropriate model listeners.
	 *
	 * @param marketingEventAnnouncement the marketing event announcement
	 * @return the marketing event announcement that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public MarketingEventAnnouncement addMarketingEventAnnouncement(
		MarketingEventAnnouncement marketingEventAnnouncement) {
		marketingEventAnnouncement.setNew(true);

		return marketingEventAnnouncementPersistence.update(marketingEventAnnouncement);
	}

	/**
	 * Creates a new marketing event announcement with the primary key. Does not add the marketing event announcement to the database.
	 *
	 * @param marketingEventAnnouncementId the primary key for the new marketing event announcement
	 * @return the new marketing event announcement
	 */
	@Override
	public MarketingEventAnnouncement createMarketingEventAnnouncement(
		long marketingEventAnnouncementId) {
		return marketingEventAnnouncementPersistence.create(marketingEventAnnouncementId);
	}

	/**
	 * Deletes the marketing event announcement with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param marketingEventAnnouncementId the primary key of the marketing event announcement
	 * @return the marketing event announcement that was removed
	 * @throws PortalException if a marketing event announcement with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public MarketingEventAnnouncement deleteMarketingEventAnnouncement(
		long marketingEventAnnouncementId) throws PortalException {
		return marketingEventAnnouncementPersistence.remove(marketingEventAnnouncementId);
	}

	/**
	 * Deletes the marketing event announcement from the database. Also notifies the appropriate model listeners.
	 *
	 * @param marketingEventAnnouncement the marketing event announcement
	 * @return the marketing event announcement that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public MarketingEventAnnouncement deleteMarketingEventAnnouncement(
		MarketingEventAnnouncement marketingEventAnnouncement) {
		return marketingEventAnnouncementPersistence.remove(marketingEventAnnouncement);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(MarketingEventAnnouncement.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return marketingEventAnnouncementPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.www.marketing.events.model.impl.MarketingEventAnnouncementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return marketingEventAnnouncementPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.www.marketing.events.model.impl.MarketingEventAnnouncementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return marketingEventAnnouncementPersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return marketingEventAnnouncementPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return marketingEventAnnouncementPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public MarketingEventAnnouncement fetchMarketingEventAnnouncement(
		long marketingEventAnnouncementId) {
		return marketingEventAnnouncementPersistence.fetchByPrimaryKey(marketingEventAnnouncementId);
	}

	/**
	 * Returns the marketing event announcement with the primary key.
	 *
	 * @param marketingEventAnnouncementId the primary key of the marketing event announcement
	 * @return the marketing event announcement
	 * @throws PortalException if a marketing event announcement with the primary key could not be found
	 */
	@Override
	public MarketingEventAnnouncement getMarketingEventAnnouncement(
		long marketingEventAnnouncementId) throws PortalException {
		return marketingEventAnnouncementPersistence.findByPrimaryKey(marketingEventAnnouncementId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(marketingEventAnnouncementLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(MarketingEventAnnouncement.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"marketingEventAnnouncementId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(marketingEventAnnouncementLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(MarketingEventAnnouncement.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"marketingEventAnnouncementId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(marketingEventAnnouncementLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(MarketingEventAnnouncement.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"marketingEventAnnouncementId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return marketingEventAnnouncementLocalService.deleteMarketingEventAnnouncement((MarketingEventAnnouncement)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return marketingEventAnnouncementPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the marketing event announcements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.www.marketing.events.model.impl.MarketingEventAnnouncementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of marketing event announcements
	 * @param end the upper bound of the range of marketing event announcements (not inclusive)
	 * @return the range of marketing event announcements
	 */
	@Override
	public List<MarketingEventAnnouncement> getMarketingEventAnnouncements(
		int start, int end) {
		return marketingEventAnnouncementPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of marketing event announcements.
	 *
	 * @return the number of marketing event announcements
	 */
	@Override
	public int getMarketingEventAnnouncementsCount() {
		return marketingEventAnnouncementPersistence.countAll();
	}

	/**
	 * Updates the marketing event announcement in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param marketingEventAnnouncement the marketing event announcement
	 * @return the marketing event announcement that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public MarketingEventAnnouncement updateMarketingEventAnnouncement(
		MarketingEventAnnouncement marketingEventAnnouncement) {
		return marketingEventAnnouncementPersistence.update(marketingEventAnnouncement);
	}

	/**
	 * Returns the marketing event local service.
	 *
	 * @return the marketing event local service
	 */
	public com.liferay.osb.www.marketing.events.service.MarketingEventLocalService getMarketingEventLocalService() {
		return marketingEventLocalService;
	}

	/**
	 * Sets the marketing event local service.
	 *
	 * @param marketingEventLocalService the marketing event local service
	 */
	public void setMarketingEventLocalService(
		com.liferay.osb.www.marketing.events.service.MarketingEventLocalService marketingEventLocalService) {
		this.marketingEventLocalService = marketingEventLocalService;
	}

	/**
	 * Returns the marketing event persistence.
	 *
	 * @return the marketing event persistence
	 */
	public MarketingEventPersistence getMarketingEventPersistence() {
		return marketingEventPersistence;
	}

	/**
	 * Sets the marketing event persistence.
	 *
	 * @param marketingEventPersistence the marketing event persistence
	 */
	public void setMarketingEventPersistence(
		MarketingEventPersistence marketingEventPersistence) {
		this.marketingEventPersistence = marketingEventPersistence;
	}

	/**
	 * Returns the marketing event announcement local service.
	 *
	 * @return the marketing event announcement local service
	 */
	public MarketingEventAnnouncementLocalService getMarketingEventAnnouncementLocalService() {
		return marketingEventAnnouncementLocalService;
	}

	/**
	 * Sets the marketing event announcement local service.
	 *
	 * @param marketingEventAnnouncementLocalService the marketing event announcement local service
	 */
	public void setMarketingEventAnnouncementLocalService(
		MarketingEventAnnouncementLocalService marketingEventAnnouncementLocalService) {
		this.marketingEventAnnouncementLocalService = marketingEventAnnouncementLocalService;
	}

	/**
	 * Returns the marketing event announcement persistence.
	 *
	 * @return the marketing event announcement persistence
	 */
	public MarketingEventAnnouncementPersistence getMarketingEventAnnouncementPersistence() {
		return marketingEventAnnouncementPersistence;
	}

	/**
	 * Sets the marketing event announcement persistence.
	 *
	 * @param marketingEventAnnouncementPersistence the marketing event announcement persistence
	 */
	public void setMarketingEventAnnouncementPersistence(
		MarketingEventAnnouncementPersistence marketingEventAnnouncementPersistence) {
		this.marketingEventAnnouncementPersistence = marketingEventAnnouncementPersistence;
	}

	/**
	 * Returns the marketing event session local service.
	 *
	 * @return the marketing event session local service
	 */
	public com.liferay.osb.www.marketing.events.service.MarketingEventSessionLocalService getMarketingEventSessionLocalService() {
		return marketingEventSessionLocalService;
	}

	/**
	 * Sets the marketing event session local service.
	 *
	 * @param marketingEventSessionLocalService the marketing event session local service
	 */
	public void setMarketingEventSessionLocalService(
		com.liferay.osb.www.marketing.events.service.MarketingEventSessionLocalService marketingEventSessionLocalService) {
		this.marketingEventSessionLocalService = marketingEventSessionLocalService;
	}

	/**
	 * Returns the marketing event session persistence.
	 *
	 * @return the marketing event session persistence
	 */
	public MarketingEventSessionPersistence getMarketingEventSessionPersistence() {
		return marketingEventSessionPersistence;
	}

	/**
	 * Sets the marketing event session persistence.
	 *
	 * @param marketingEventSessionPersistence the marketing event session persistence
	 */
	public void setMarketingEventSessionPersistence(
		MarketingEventSessionPersistence marketingEventSessionPersistence) {
		this.marketingEventSessionPersistence = marketingEventSessionPersistence;
	}

	/**
	 * Returns the marketing event session finder.
	 *
	 * @return the marketing event session finder
	 */
	public MarketingEventSessionFinder getMarketingEventSessionFinder() {
		return marketingEventSessionFinder;
	}

	/**
	 * Sets the marketing event session finder.
	 *
	 * @param marketingEventSessionFinder the marketing event session finder
	 */
	public void setMarketingEventSessionFinder(
		MarketingEventSessionFinder marketingEventSessionFinder) {
		this.marketingEventSessionFinder = marketingEventSessionFinder;
	}

	/**
	 * Returns the marketing event session room local service.
	 *
	 * @return the marketing event session room local service
	 */
	public com.liferay.osb.www.marketing.events.service.MarketingEventSessionRoomLocalService getMarketingEventSessionRoomLocalService() {
		return marketingEventSessionRoomLocalService;
	}

	/**
	 * Sets the marketing event session room local service.
	 *
	 * @param marketingEventSessionRoomLocalService the marketing event session room local service
	 */
	public void setMarketingEventSessionRoomLocalService(
		com.liferay.osb.www.marketing.events.service.MarketingEventSessionRoomLocalService marketingEventSessionRoomLocalService) {
		this.marketingEventSessionRoomLocalService = marketingEventSessionRoomLocalService;
	}

	/**
	 * Returns the marketing event session room persistence.
	 *
	 * @return the marketing event session room persistence
	 */
	public MarketingEventSessionRoomPersistence getMarketingEventSessionRoomPersistence() {
		return marketingEventSessionRoomPersistence;
	}

	/**
	 * Sets the marketing event session room persistence.
	 *
	 * @param marketingEventSessionRoomPersistence the marketing event session room persistence
	 */
	public void setMarketingEventSessionRoomPersistence(
		MarketingEventSessionRoomPersistence marketingEventSessionRoomPersistence) {
		this.marketingEventSessionRoomPersistence = marketingEventSessionRoomPersistence;
	}

	/**
	 * Returns the marketing event sponsor local service.
	 *
	 * @return the marketing event sponsor local service
	 */
	public com.liferay.osb.www.marketing.events.service.MarketingEventSponsorLocalService getMarketingEventSponsorLocalService() {
		return marketingEventSponsorLocalService;
	}

	/**
	 * Sets the marketing event sponsor local service.
	 *
	 * @param marketingEventSponsorLocalService the marketing event sponsor local service
	 */
	public void setMarketingEventSponsorLocalService(
		com.liferay.osb.www.marketing.events.service.MarketingEventSponsorLocalService marketingEventSponsorLocalService) {
		this.marketingEventSponsorLocalService = marketingEventSponsorLocalService;
	}

	/**
	 * Returns the marketing event sponsor persistence.
	 *
	 * @return the marketing event sponsor persistence
	 */
	public MarketingEventSponsorPersistence getMarketingEventSponsorPersistence() {
		return marketingEventSponsorPersistence;
	}

	/**
	 * Sets the marketing event sponsor persistence.
	 *
	 * @param marketingEventSponsorPersistence the marketing event sponsor persistence
	 */
	public void setMarketingEventSponsorPersistence(
		MarketingEventSponsorPersistence marketingEventSponsorPersistence) {
		this.marketingEventSponsorPersistence = marketingEventSponsorPersistence;
	}

	/**
	 * Returns the marketing event sponsor level local service.
	 *
	 * @return the marketing event sponsor level local service
	 */
	public com.liferay.osb.www.marketing.events.service.MarketingEventSponsorLevelLocalService getMarketingEventSponsorLevelLocalService() {
		return marketingEventSponsorLevelLocalService;
	}

	/**
	 * Sets the marketing event sponsor level local service.
	 *
	 * @param marketingEventSponsorLevelLocalService the marketing event sponsor level local service
	 */
	public void setMarketingEventSponsorLevelLocalService(
		com.liferay.osb.www.marketing.events.service.MarketingEventSponsorLevelLocalService marketingEventSponsorLevelLocalService) {
		this.marketingEventSponsorLevelLocalService = marketingEventSponsorLevelLocalService;
	}

	/**
	 * Returns the marketing event sponsor level persistence.
	 *
	 * @return the marketing event sponsor level persistence
	 */
	public MarketingEventSponsorLevelPersistence getMarketingEventSponsorLevelPersistence() {
		return marketingEventSponsorLevelPersistence;
	}

	/**
	 * Sets the marketing event sponsor level persistence.
	 *
	 * @param marketingEventSponsorLevelPersistence the marketing event sponsor level persistence
	 */
	public void setMarketingEventSponsorLevelPersistence(
		MarketingEventSponsorLevelPersistence marketingEventSponsorLevelPersistence) {
		this.marketingEventSponsorLevelPersistence = marketingEventSponsorLevelPersistence;
	}

	/**
	 * Returns the marketing event user local service.
	 *
	 * @return the marketing event user local service
	 */
	public com.liferay.osb.www.marketing.events.service.MarketingEventUserLocalService getMarketingEventUserLocalService() {
		return marketingEventUserLocalService;
	}

	/**
	 * Sets the marketing event user local service.
	 *
	 * @param marketingEventUserLocalService the marketing event user local service
	 */
	public void setMarketingEventUserLocalService(
		com.liferay.osb.www.marketing.events.service.MarketingEventUserLocalService marketingEventUserLocalService) {
		this.marketingEventUserLocalService = marketingEventUserLocalService;
	}

	/**
	 * Returns the marketing event user persistence.
	 *
	 * @return the marketing event user persistence
	 */
	public MarketingEventUserPersistence getMarketingEventUserPersistence() {
		return marketingEventUserPersistence;
	}

	/**
	 * Sets the marketing event user persistence.
	 *
	 * @param marketingEventUserPersistence the marketing event user persistence
	 */
	public void setMarketingEventUserPersistence(
		MarketingEventUserPersistence marketingEventUserPersistence) {
		this.marketingEventUserPersistence = marketingEventUserPersistence;
	}

	/**
	 * Returns the marketing event user finder.
	 *
	 * @return the marketing event user finder
	 */
	public MarketingEventUserFinder getMarketingEventUserFinder() {
		return marketingEventUserFinder;
	}

	/**
	 * Sets the marketing event user finder.
	 *
	 * @param marketingEventUserFinder the marketing event user finder
	 */
	public void setMarketingEventUserFinder(
		MarketingEventUserFinder marketingEventUserFinder) {
		this.marketingEventUserFinder = marketingEventUserFinder;
	}

	/**
	 * Returns the social link local service.
	 *
	 * @return the social link local service
	 */
	public com.liferay.osb.www.marketing.events.service.SocialLinkLocalService getSocialLinkLocalService() {
		return socialLinkLocalService;
	}

	/**
	 * Sets the social link local service.
	 *
	 * @param socialLinkLocalService the social link local service
	 */
	public void setSocialLinkLocalService(
		com.liferay.osb.www.marketing.events.service.SocialLinkLocalService socialLinkLocalService) {
		this.socialLinkLocalService = socialLinkLocalService;
	}

	/**
	 * Returns the social link persistence.
	 *
	 * @return the social link persistence
	 */
	public SocialLinkPersistence getSocialLinkPersistence() {
		return socialLinkPersistence;
	}

	/**
	 * Sets the social link persistence.
	 *
	 * @param socialLinkPersistence the social link persistence
	 */
	public void setSocialLinkPersistence(
		SocialLinkPersistence socialLinkPersistence) {
		this.socialLinkPersistence = socialLinkPersistence;
	}

	/**
	 * Returns the social link type local service.
	 *
	 * @return the social link type local service
	 */
	public com.liferay.osb.www.marketing.events.service.SocialLinkTypeLocalService getSocialLinkTypeLocalService() {
		return socialLinkTypeLocalService;
	}

	/**
	 * Sets the social link type local service.
	 *
	 * @param socialLinkTypeLocalService the social link type local service
	 */
	public void setSocialLinkTypeLocalService(
		com.liferay.osb.www.marketing.events.service.SocialLinkTypeLocalService socialLinkTypeLocalService) {
		this.socialLinkTypeLocalService = socialLinkTypeLocalService;
	}

	/**
	 * Returns the social link type persistence.
	 *
	 * @return the social link type persistence
	 */
	public SocialLinkTypePersistence getSocialLinkTypePersistence() {
		return socialLinkTypePersistence;
	}

	/**
	 * Sets the social link type persistence.
	 *
	 * @param socialLinkTypePersistence the social link type persistence
	 */
	public void setSocialLinkTypePersistence(
		SocialLinkTypePersistence socialLinkTypePersistence) {
		this.socialLinkTypePersistence = socialLinkTypePersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {
		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.liferay.osb.www.marketing.events.model.MarketingEventAnnouncement",
			marketingEventAnnouncementLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.osb.www.marketing.events.model.MarketingEventAnnouncement");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return MarketingEventAnnouncementLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return MarketingEventAnnouncement.class;
	}

	protected String getModelClassName() {
		return MarketingEventAnnouncement.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = marketingEventAnnouncementPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.liferay.osb.www.marketing.events.service.MarketingEventLocalService.class)
	protected com.liferay.osb.www.marketing.events.service.MarketingEventLocalService marketingEventLocalService;
	@BeanReference(type = MarketingEventPersistence.class)
	protected MarketingEventPersistence marketingEventPersistence;
	@BeanReference(type = MarketingEventAnnouncementLocalService.class)
	protected MarketingEventAnnouncementLocalService marketingEventAnnouncementLocalService;
	@BeanReference(type = MarketingEventAnnouncementPersistence.class)
	protected MarketingEventAnnouncementPersistence marketingEventAnnouncementPersistence;
	@BeanReference(type = com.liferay.osb.www.marketing.events.service.MarketingEventSessionLocalService.class)
	protected com.liferay.osb.www.marketing.events.service.MarketingEventSessionLocalService marketingEventSessionLocalService;
	@BeanReference(type = MarketingEventSessionPersistence.class)
	protected MarketingEventSessionPersistence marketingEventSessionPersistence;
	@BeanReference(type = MarketingEventSessionFinder.class)
	protected MarketingEventSessionFinder marketingEventSessionFinder;
	@BeanReference(type = com.liferay.osb.www.marketing.events.service.MarketingEventSessionRoomLocalService.class)
	protected com.liferay.osb.www.marketing.events.service.MarketingEventSessionRoomLocalService marketingEventSessionRoomLocalService;
	@BeanReference(type = MarketingEventSessionRoomPersistence.class)
	protected MarketingEventSessionRoomPersistence marketingEventSessionRoomPersistence;
	@BeanReference(type = com.liferay.osb.www.marketing.events.service.MarketingEventSponsorLocalService.class)
	protected com.liferay.osb.www.marketing.events.service.MarketingEventSponsorLocalService marketingEventSponsorLocalService;
	@BeanReference(type = MarketingEventSponsorPersistence.class)
	protected MarketingEventSponsorPersistence marketingEventSponsorPersistence;
	@BeanReference(type = com.liferay.osb.www.marketing.events.service.MarketingEventSponsorLevelLocalService.class)
	protected com.liferay.osb.www.marketing.events.service.MarketingEventSponsorLevelLocalService marketingEventSponsorLevelLocalService;
	@BeanReference(type = MarketingEventSponsorLevelPersistence.class)
	protected MarketingEventSponsorLevelPersistence marketingEventSponsorLevelPersistence;
	@BeanReference(type = com.liferay.osb.www.marketing.events.service.MarketingEventUserLocalService.class)
	protected com.liferay.osb.www.marketing.events.service.MarketingEventUserLocalService marketingEventUserLocalService;
	@BeanReference(type = MarketingEventUserPersistence.class)
	protected MarketingEventUserPersistence marketingEventUserPersistence;
	@BeanReference(type = MarketingEventUserFinder.class)
	protected MarketingEventUserFinder marketingEventUserFinder;
	@BeanReference(type = com.liferay.osb.www.marketing.events.service.SocialLinkLocalService.class)
	protected com.liferay.osb.www.marketing.events.service.SocialLinkLocalService socialLinkLocalService;
	@BeanReference(type = SocialLinkPersistence.class)
	protected SocialLinkPersistence socialLinkPersistence;
	@BeanReference(type = com.liferay.osb.www.marketing.events.service.SocialLinkTypeLocalService.class)
	protected com.liferay.osb.www.marketing.events.service.SocialLinkTypeLocalService socialLinkTypeLocalService;
	@BeanReference(type = SocialLinkTypePersistence.class)
	protected SocialLinkTypePersistence socialLinkTypePersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}