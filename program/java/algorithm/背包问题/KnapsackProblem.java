package net.saisimon.qlcoder;

import java.util.List;

/**
 * 背包问题(Knapsack problem) —— 动态规划(Dynamic programming DP)
 * 
 * 	有一个包和n个物品，包的容量为m，每个物品都有各自的体积和价值
 * 	问当从这n个物品中选择多个物品放在包里而物品体积总数不超过包的容量m时，能够得到的最大价值是多少
 * 	
 * 	01背包问题附加条件 —— 对于每个物品不可以取多次，最多只能取一次，之所以叫做01背包，0表示不取，1表示取
 * 	
 * 	思考动态规划的第一点----最优子结构
 * 	思考动态规划的第二点----子问题重叠
 * 	思考动态规划的第三点----边界
 * 	思考动态规划的第四点----子问题独立
 * 	思考动态规划的第五点----做备忘录
 * 	思考动态规划的第六点----时间分析
 * 
 * 	分析方法： 问题实例 P(n) 的最优解S(n) 蕴含 问题实例 P(n-1) 的最优解S(n-1); 在S(n-1)的基础上构造 S(n)
 * 
 * @author Saisimon
 *
 */
public class KnapsackProblem {
	
	/**
	 * 求解01背包问题
	 * 
	 * @param items 物品列表
	 * @param maxVolume 背包最大容量
	 * @return 返回最优解
	 */
	public int getMaxWorth(List<Item> items, int maxVolume) {
		if (maxVolume <= 0) {
			return 0;
		} else if (items.size() == 1) {
			// 边界
			if (items.get(0).getVolume() <= maxVolume) {
				// 最后的所需体积是否足够
				return items.get(0).getWorth();
			} else {
				return 0;
			}
		} else {
			List<Item> subItems = items.subList(0, items.size() - 1);
			Item last = items.get(items.size() - 1);
			int tmp = maxVolume - last.getVolume();
			/*
			 * 母问题分解为在子问题中选择其中最优解的问题
			 * Fn(items, maxVolume) = max(Fn(items.sub(0, last - 1), maxVolume - items[last].volume)
			 * 								Fn(items.sub(0, last - 1), maxVolume));
			 */
			return Math.max(tmp < 0 ? 0 : getMaxWorth(subItems, tmp) + last.getWorth(), 
					getMaxWorth(subItems, maxVolume));
		}
	}
	
}

/**
 * 物品
 * 
 * @author Saisimon
 *
 */
class Item {
	/**
	 * 价值
	 */
	private int worth;
	/**
	 * 体积
	 */
	private int volume;
	
	public int getWorth() {
		return worth;
	}
	
	public void setWorth(int worth) {
		this.worth = worth;
	}
	
	public int getVolume() {
		return volume;
	}
	
	public void setVolume(int volume) {
		this.volume = volume;
	}
	
	public Item(int worth, int volume) {
		this.worth = worth;
		this.volume = volume;
	}
	
	@Override
	public String toString() {
		return "[worth : " + worth + ",volume : " + volume + "]";
	}
}
