/*
 * Copyright (C) 2004-2017, GoodData(R) Corporation. All rights reserved.
 * This source code is licensed under the BSD-style license found in the
 * LICENSE.txt file in the root directory of this source tree.
 */
package com.gooddata.collections;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import static com.gooddata.util.Validate.notNull;

/**
 * Wrapper over pageable GDC list.
 *
 * @param <E> type of collection elements
 */
public class PageableList<E> implements List<E> {

    static final String ITEMS_NODE = "items";
    static final String LINKS_NODE = "links";
    static final String PAGING_NODE = "paging";

    private final List<E> items;
    private final Paging paging;
    private final Map<String, String> links;

    /**
     * Creates empty list with no next page.
     */
    public PageableList() {
        this(Collections.emptyList(), null);
    }

    /**
     * Creates list wrapping provided items and next page.
     *
     * @param items  to be wrapped
     * @param paging page description, might be <code>null</code>
     */
    public PageableList(final List<E> items, final Paging paging) {
        this(items, paging, null);
    }

    /**
     * Creates list wrapping provided items, next page and links.
     *
     * @param items  to be wrapped
     * @param paging page description, might be <code>null</code>
     * @param links links, might be <code>null</code>
     */
    public PageableList(final List<E> items, final Paging paging, final Map<String, String> links) {
        this.items = notNull(items, "items");
        this.paging = paging;
        this.links = links;
    }

    /**
     * @return all items as list
     */
    public List<E> collectAll() {
        return items;
    }

    /**
     * Returns description of the next page.
     *
     * @return next page, might be <code>null</code>
     */
    public Page getNextPage() {
        return paging == null ? null : paging.getNext();
    }

    /**
     * Signals whether there are more subsequent pages or the last page has been reached
     * @return true if there are more results to come
     */
    public boolean hasNextPage() {
        return getNextPage() != null;
    }

    /**
     * Returns map of links.
     *
     * @return map of links, might be <code>null</code>
     */
    public Map<String, String> getLinks() {
        return links;
    }

    /**
     * Returns description of the current collection page.
     *
     * @return current collection page, might be <code>null</code>
     */
    public Paging getPaging() {
        return paging;
    }

    /**
     * Returns size of the items collection on a current page.
     *
     * @return size of the items collection on a current page
     */
    @Override
    public int size() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Whether current page items contain the specified object.
     */
    @Override
    public boolean contains(final Object o) {
        return items.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return items.iterator();
    }

    /**
     * @return the list of current page items only
     */
    public List<E> getCurrentPageItems() { return items; }

    @Override
    public Object[] toArray() {
        return items.toArray();
    }

    @Override
    public <T> T[] toArray(final T[] a) {
        return items.toArray(a);
    }

    @Override
    public boolean add(final E e) {
        return items.add(e);
    }

    @Override
    public boolean remove(final Object o) {
        return items.remove(o);
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        return items.containsAll(c);
    }

    @Override
    public boolean addAll(final Collection<? extends E> c) {
        return items.addAll(c);
    }

    @Override
    public boolean addAll(final int index, final Collection<? extends E> c) {
        return items.addAll(index, c);
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        return items.removeAll(c);
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        return items.retainAll(c);
    }

    @Override
    public void clear() {
        items.clear();
    }

    @Override
    public E get(final int index) {
        return items.get(index);
    }

    @Override
    public E set(final int index, final E element) {
        return items.set(index, element);
    }

    @Override
    public void add(final int index, final E element) {
        items.add(index, element);
    }

    @Override
    public E remove(final int index) {
        return items.remove(index);
    }

    @Override
    public int indexOf(final Object o) {
        return items.indexOf(o);
    }

    @Override
    public int lastIndexOf(final Object o) {
        return items.lastIndexOf(o);
    }

    @Override
    public ListIterator<E> listIterator() {
        return items.listIterator();
    }

    @Override
    public ListIterator<E> listIterator(final int index) {
        return items.listIterator(index);
    }

    @Override
    public List<E> subList(final int fromIndex, final int toIndex) {
        return items.subList(fromIndex, toIndex);
    }

    Iterator<E> getItemsIterator() {
        return items.iterator();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final PageableList<?> that = (PageableList<?>) o;

        if (!items.equals(that.items)) return false;
        if (getPaging() != null ? !getPaging().equals(that.getPaging()) : that.getPaging() != null) return false;
        return getLinks() != null ? getLinks().equals(that.getLinks()) : that.getLinks() == null;
    }

    @Override
    public int hashCode() {
        int result = items.hashCode();
        result = 31 * result + (getPaging() != null ? getPaging().hashCode() : 0);
        result = 31 * result + (getLinks() != null ? getLinks().hashCode() : 0);
        return result;
    }
}
