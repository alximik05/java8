package org.store.api.book;

import java.io.*;
import java.net.URI;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.math.NumberUtils;
import org.shop.model.Book;
import org.store.util.DateUtil;

import com.google.gson.Gson;
import sun.nio.ch.SimpleAsynchronousFileChannelImpl;

public class BookReaderImpl implements BookReader {
    private static final Gson GSON = new Gson();

    @Override
    public List<Book> readCsv(String path) {
        List<Book> books = new ArrayList<>();
        String line = "";
        String cvsSplitBy = ",";
        int idx = 0;
//        InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);


        try (AsynchronousFileChannel channel = AsynchronousFileChannel.open(Paths.get(path))) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(102);
            Future<Integer> read = channel.read(byteBuffer, 0);
            try {
                Thread.sleep(1024);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(new String(byteBuffer.array()));
        } catch (IOException e) {
            e.printStackTrace();
        }
//
//        try (InputStreamReader inputStreamReader = new InputStreamReader(is);
//             BufferedReader br = new BufferedReader(inputStreamReader)) {
//            while ((line = br.readLine()) != null) {
//                idx++;
//                if (idx == 1) {
//                    continue;
//                }
//
//                String[] tokens = line.split(cvsSplitBy);
//                Book book = new Book();
//                book.setId(NumberUtils.toInt(tokens[0]));
//                book.setName(tokens[1]);
//                book.setPages(NumberUtils.toInt(tokens[2]));
//                book.setAuthor(tokens[3]);
//                book.setYear(tokens[4]);
//                if (book.getYear().isEmpty()) {
//                    book.setYear("N/A");
//                }
//                book.setPreview(tokens[5]);
//                book.setPublication(DateUtil.strToDate(tokens[6]));
//                books.add(book);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return books;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Book> readJson(String path) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        List<?> items = GSON.fromJson(reader, List.class);
        List<Book> books = new ArrayList<>();
        for (Object item : items) {
            Map<String, String> data = (Map<String, String>) item;
            Book book = new Book();
            book.setId(NumberUtils.toInt(data.get("id")));
            book.setName(data.get("name"));
            book.setPages(NumberUtils.toInt(data.get("pages")));
            book.setAuthor(data.get("author"));
            if (data.containsKey("year")) {
                book.setYear(data.get("year"));
            } else {
                book.setYear("N/A");
            }

            book.setPreview(data.get("preview"));
            book.setPublication(DateUtil.strToDate(data.get("publication")));

            books.add(book);
        }

        return books;
    }

}
