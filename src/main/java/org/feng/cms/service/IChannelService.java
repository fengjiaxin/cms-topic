package org.feng.cms.service;

import java.util.List;

import org.feng.cms.model.Channel;
import org.feng.cms.model.ChannelTree;

public interface IChannelService {
	/**
	 * 添加栏目信息
	 * @param channel
	 * @param pid
	 * @return: void
	 */
	public void add(Channel channel ,Integer pid);
	/**
	 * 更新栏目
	 * @param channel
	 * @return: void
	 */
	public void update(Channel channel);
	/**
	 * 删除栏目
	 * @param id
	 * @return: void
	 */
	public void delete(int id);
	/**
	 * 清空栏目中的文章
	 * @param id
	 * @return: void
	 */
	public void clearTopic(int id);
	/**
	 * 加载栏目
	 * @param id
	 * @return
	 * @return: Channel
	 */
	public Channel load(int id);
	/**
	 * 根据父id加载栏目，该方面首先检查systemcontext中是否存在排序，如果没有，把orders加进去
	 * @param pid
	 * @return
	 * @return: List<Channel>
	 */
	public List<Channel> listByParent(Integer pid);
	
	public List<ChannelTree> generateTree();
	public List<ChannelTree> generateTreebyParent(Integer pid);

}
