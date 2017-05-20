package net.saisimon.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>56. Merge Intervals</h1>
 * 	<p>Given a collection of intervals, merge all overlapping intervals.
 * 
 * 	<p>For example,
 * 		<br>&nbsp; Given [1,3],[2,6],[8,10],[15,18],
 * 		<br>&nbsp; return [1,6],[8,10],[15,18].
 * 
 * 	<p>Definition for an interval.
 * 	<br>&nbsp; public class Interval {
 *     	<br>&nbsp;&nbsp; int start;
 *     	<br>&nbsp;&nbsp; int end;
 *     	<br>&nbsp;&nbsp; Interval() { start = 0; end = 0; }
 *     	<br>&nbsp;&nbsp; Interval(int s, int e) { start = s; end = e; }
 * 	<br>&nbsp; }
 * 
 * @author Saisimon
 *
 */
public class MergeIntervals {
	
	public List<Interval> merge(List<Interval> intervals) {
		if (intervals == null || intervals.size() < 2) {
			return intervals;
		}
		List<Interval> res = new ArrayList<>();
		// 根据左区间的大小进行排序
		intervals.sort((i1, i2) -> {
			if (i1.start > i2.start) {
				return 1;
			} else if (i1.start < i2.start) {
				return -1;
			} else {
				return 0;
			}
		});
		int start = intervals.get(0).start;
		int end = intervals.get(0).end;
		// 遍历比较是否有重叠的区间
		for (int i = 1; i < intervals.size(); i++) {
			Interval interval = intervals.get(i);
			if (interval.start <= end) {
				end = Math.max(end, interval.end);
			} else {
				res.add(new Interval(start, end));
				start = interval.start;
				end = interval.end;
			}
		}
		res.add(new Interval(start, end));
		return res;
    }
	
	class Interval {
		int start;
		int end;

		Interval() {
			start = 0;
			end = 0;
		}

		Interval(int s, int e) {
			start = s;
			end = e;
		}
	}
	
}
