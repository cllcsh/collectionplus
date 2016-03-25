package org.express.common.bean;

public abstract class PageBean 
{
	private Integer pageCount = 10;
	private Integer totalPage;
	private Integer pageIndex = 1;
	private Integer totalCount;
	private Integer orderMarker = -1;
	
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer index) {
		this.pageIndex = index;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public Integer getOrderMarker() {
		return orderMarker;
	}
	public void setOrderMarker(Integer orderMarker) {
		this.orderMarker = orderMarker;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
		if(totalCount != null)
		{
			if(totalCount < pageCount)
				totalPage = 1;
			else
			{
				if(totalCount % this.pageCount > 0)
				{
					this.totalPage = (this.totalCount / this.pageCount) + 1;
				}
				else
				{
					this.totalPage = this.totalCount / this.pageCount;
				}
			}
		}
	}
}
