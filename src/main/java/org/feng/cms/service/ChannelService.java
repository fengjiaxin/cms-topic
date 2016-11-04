package org.feng.cms.service;

import java.util.List;

import javax.inject.Inject;

import org.feng.cms.dao.IChannelDao;
import org.feng.cms.model.Channel;
import org.feng.cms.model.ChannelTree;
import org.feng.cms.model.CmsException;
import org.springframework.stereotype.Service;

@Service("channelService")
public class ChannelService implements IChannelService {
	private IChannelDao channelDao;
	public IChannelDao getChannelDao() {
		return channelDao;
	}
	@Inject
	public void setChannelDao(IChannelDao channelDao) {
		this.channelDao = channelDao;
	}

	@Override
	public void add(Channel channel, Integer pid) {
		Integer orders = channelDao.getMaxOrderByParent(pid);
		if(pid!=null && pid>0){
			Channel pc = channelDao.load(pid);
			if(pc==null) throw new CmsException("要添加栏目的父对象不正确");
			channel.setParent(pc);
		}
		channel.setOrders(orders+1);
		channelDao.add(channel);
	}

	@Override
	public void update(Channel channel) {
		channelDao.update(channel);
	}

	@Override
	public void delete(int id) {
		//1需要判断是否存在子栏目
		List<Channel> cs = channelDao.listByParent(id);
		//todo 需要判断是否存在文章
		//需要删除和组的关联关系
		if(cs!=null &&cs.size()>0) throw new CmsException("要删除的栏目还有子栏目，无法删除");
		channelDao.delete(id);
		
	}

	@Override
	public void clearTopic(int id) {
		//实现了文章模块后才实现该方法
	}

	@Override
	public Channel load(int id) {
		return channelDao.load(id);
	}

	@Override
	public List<Channel> listByParent(Integer pid) {
		return channelDao.listByParent(pid);
	}
	@Override
	public List<ChannelTree> generateTree() {
		return channelDao.generateTree();
	}
	@Override
	public List<ChannelTree> generateTreebyParent(Integer pid) {
		return channelDao.generateTreeByParent(pid);
	}
	@Override
	public void updateSort(Integer[] ids) {
		channelDao.updateSort(ids);
	}

}
