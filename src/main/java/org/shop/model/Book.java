package org.shop.model;

import java.util.Date;
import java.util.Objects;

public class Book {
	private int id;
	
	private String name;
	
	private int pages;
	
	private String author;
	
	private String year;
	
	private String preview;
	
	private Date publication;

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Date getPublication() {
		return publication;
	}

	public void setPublication(Date publication) {
		this.publication = publication;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", pages=" + pages + ", author=" + author + ", year=" + year
				+ ", preview=" + preview + ", publication=" + publication + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Book book = (Book) o;

		if (id != book.id) return false;
		if (pages != book.pages) return false;
		if (name != null ? !name.equals(book.name) : book.name != null) return false;
		if (author != null ? !author.equals(book.author) : book.author != null) return false;
		if (year != null ? !year.equals(book.year) : book.year != null) return false;
		if (preview != null ? !preview.equals(book.preview) : book.preview != null) return false;
		return publication != null ? publication.equals(book.publication) : book.publication == null;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, pages, author, year, preview, publication);
	}
}
