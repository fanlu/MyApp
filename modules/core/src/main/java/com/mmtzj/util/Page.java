package com.mmtzj.util;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 与具体ORM实现无关的分页参数及查询结果封装.
 * 
 * @param <T>
 *            Page中记录的类型.
 * 
 */
public class Page<T> implements Iterable<T> {
    // -- 公共变量 --//
    public static final String ASC = "asc";
    public static final String DESC = "desc";

    // -- 分页查询参数 --//
    public int pageNo = 1;
    public int pageSize = Constant.PAGE_SIZE;
    protected String orderBy = null;
    protected String order = null;
    /** 总记录数 */
    public long totalItems = -1;
    public long totalPages = 1;
    public List<Long> slider = new ArrayList<Long>();

    // -- 返回结果 --//
    public List<T> result = new ArrayList<T>();

    // 查询参数的Map
    private Map<String, Object> param = new HashMap<String, Object>();

    // -- 构造函数 --//
    public Page() {
    }

    public Page(int pageNo) {
        setPageNo(pageNo);
    }

    public Page(int pageNo, int pageSize) {
        setPageNo(pageNo);
        setPageSize(pageSize);
    }

    /** 默认为id倒序的构造方法 */
    public Page(int pageNo, int pageSize, Map<String, Object> param) {
        setPageNo(pageNo);
        setPageSize(pageSize);
        setParam(param);
        setOrderBy("id");
        setOrder(DESC);
    }

    /**
     * 简便的生成Page
     * 
     * @param pageNo
     *            当前页
     * @param pageSize
     *            每页显示多少条
     * @param param
     *            查询参数
     * @param orderBy
     *            根据哪列排序
     * @param order
     *            正序还是倒序
     */
    public Page(int pageNo, int pageSize, Map<String, Object> param, String orderBy, String order) {
        setPageNo(pageNo);
        setPageSize(pageSize);
        setParam(param);
        setOrderBy(orderBy);
        setOrder(order);
    }

    /**
     * 根据pageNo和pageSize计算当前页最后一条记录在总结果集中的位置, 序号从1开始. 用于Oracle.
     */
    public int getEndRow() {
        return pageSize * pageNo;
    }

    /**
     * 取得下页的页号, 序号从1开始. 当前页为尾页时仍返回尾页序号.
     */
    public int getNextPage() {
        if (isHasNextPage()) {
            return pageNo + 1;
        } else {
            return pageNo;
        }
    }

    /**
     * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从0开始. 用于Mysql,Hibernate.
     */
    public int getOffset() {
        return ((pageNo - 1) * pageSize);
    }

    /**
     * 获得排序方向, 无默认值.
     */
    public String getOrder() {
        return order;
    }

    /**
     * 获得排序字段,无默认值. 多个排序字段时用','分隔.
     */
    public String getOrderBy() {
        return orderBy;
    }

    // -- 分页参数访问函数 --//
    /**
     * 获得当前页的页号,序号从1开始,默认为1.
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * 获得每页的记录数量, 默认为-1.
     */
    public int getPageSize() {
        return pageSize;
    }

    public Map<String, Object> getParam() {
        return param;
    }

    /**
     * 取得上页的页号, 序号从1开始. 当前页为首页时返回首页序号.
     */
    public int getPrePage() {
        if (isHasPrePage()) {
            return pageNo - 1;
        } else {
            return pageNo;
        }
    }

    /**
     * 获得页内的记录列表.
     */
    public List<T> getResult() {
        return result;
    }

    /**
     * 计算以当前页为中心的页面列表,如"首页,23,24,25,26,27,末页"
     * 
     *            需要计算的列表大小
     * @return pageNo列表
     */
    public List<Long> getSlider() {
        int count = Constant.PAGE_SLIDER;
        int halfSize = count / 2;
        long totalPage = getTotalPages();

        long startPageNumber = Math.max(pageNo - halfSize, 1);
        long endPageNumber = Math.min(startPageNumber + count - 1, totalPage);

        if (endPageNumber - startPageNumber < count) {
            startPageNumber = Math.max(endPageNumber - count, 1);
        }

        List<Long> result = new ArrayList<Long>();
        for (long i = startPageNumber; i <= endPageNumber; i++) {
            result.add(new Long(i));
        }
        return result;
    }

    /**
     * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始. 用于Oracle.
     */
    public int getStartRow() {
        return getOffset() + 1;
    }

    /**
     * 获得总记录数, 默认值为-1.
     */
    public long getTotalItems() {
        return totalItems;
    }

    /**
     * 根据pageSize与totalItems计算总页数, 默认值为-1.
     */
    public long getTotalPages() {
        if (totalItems < 0) {
            return -1;
        }

        long count = totalItems / pageSize;
        if (totalItems % pageSize > 0) {
            count++;
        }
        return count;
    }

    /**
     * 是否第一页.
     */
    public boolean isFirstPage() {
        return pageNo == 1;
    }

    /**
     * 是否还有下一页.
     */
    public boolean isHasNextPage() {
        return (pageNo + 1 <= getTotalPages());
    }

    // -- 访问查询结果函数 --//

    /**
     * 是否还有上一页.
     */
    public boolean isHasPrePage() {
        return (pageNo - 1 >= 1);
    }

    /**
     * 是否最后一页.
     */
    public boolean isLastPage() {
        return pageNo == getTotalPages();
    }

    /**
     * 是否已设置排序字段,无默认值.
     */
    public boolean isOrderBySetted() {
        return (StringUtils.isNotBlank(orderBy) && StringUtils.isNotBlank(order));
    }

    /**
     * 实现Iterable接口,可以for(Object item : page)遍历使用
     */
    @Override
    @SuppressWarnings("unchecked")
    public Iterator<T> iterator() {
        return result.iterator();
    }

    /**
     * 设置排序方式向.
     * 
     * @param order
     *            可选值为desc或asc,多个排序字段时用','分隔.
     */
    public void setOrder(final String order) {
        String lowcaseOrder = StringUtils.lowerCase(order);

        // 检查order字符串的合法值
        String[] orders = StringUtils.split(lowcaseOrder, ',');
        for (String orderStr : orders) {
            if (!StringUtils.equals(DESC, orderStr) && !StringUtils.equals(ASC, orderStr)) {
                throw new IllegalArgumentException("排序方向" + orderStr + "不是合法值");
            }
        }

        this.order = lowcaseOrder;
    }

    /**
     * 设置排序字段,多个排序字段时用','分隔.
     */
    public void setOrderBy(final String orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
     */
    public void setPageNo(final int pageNo) {
        this.pageNo = pageNo;

        if (pageNo < 1) {
            this.pageNo = 1;
        }
    }

    /**
     * 设置每页的记录数量.
     */
    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize;
    }

    public void setParam(Map<String, Object> param) {
        this.param = param;
    }

    /**
     * 设置页内的记录列表.
     */
    public void setResult(final List<T> result) {
        this.result = result;
    }

    public void setSlider(List<Long> slider) {
        this.slider = slider;
    }

    /**
     * 设置总记录数.
     */
    public void setTotalItems(final long totalItems) {
        this.totalItems = totalItems;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }
}