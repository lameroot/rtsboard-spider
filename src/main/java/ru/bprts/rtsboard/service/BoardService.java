package ru.bprts.rtsboard.service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.jsefa.Deserializer;
import org.jsefa.common.lowlevel.filter.HeaderAndFooterFilter;
import org.jsefa.csv.CsvDeserializer;
import org.jsefa.csv.CsvIOFactory;
import org.jsefa.csv.config.CsvConfiguration;
import ru.bprts.rtsboard.domain.Board;
import ru.bprts.rtsboard.util.Utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lameroot on 28.03.15.
 */
public class BoardService {

    public Multimap<String, Board> deserialize(File file) throws FileNotFoundException {
        CsvConfiguration csvConfiguration = new CsvConfiguration();
        csvConfiguration.setLineFilter(new HeaderAndFooterFilter(1, false, true));

        CsvDeserializer deserializer = CsvIOFactory.createFactory(csvConfiguration,Board.class).createDeserializer();

        Multimap<String, Board> boards = ArrayListMultimap.create();
        Reader reader = new FileReader(file);
        deserializer.open(reader);
        try {
            int count = 0;
            //Map<String,Board> boards = new HashMap<String, Board>();


            while (deserializer.hasNext()) {
                Board board = (Board) deserializer.next();
                //System.out.println(board.getMoment() + ": [" + board.getIssue() + "] - " +  board.getBid() + "/" + board.getAsk() + " of " + board.getNominal() + " in " + board.getPriceCurrency());
                count++;
                //if ( boards.containsKey(board.getIssue()) ) System.out.println("----" + board.getIssue());
                boards.put(board.getIssue().toLowerCase(),board);
            }
            System.out.println("count = " + count);
            System.out.println("size of boards = " + boards.size());
        } catch (Exception e){
            Utils.getOriginalCsvLine(deserializer);
        }
        deserializer.close(true);
        return boards;
    }

    public void downloadFiles() throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.setControlKeepAliveTimeout(300);
        ftpClient.setControlKeepAliveReplyTimeout(300);
        ftpClient.setDataTimeout(300);

        try {
            ftpClient.connect("ftp.nprts.ru");

            System.out.println(ftpClient.getReplyString());
            System.out.println(ftpClient.getReplyCode());

            System.out.println("positive = " + FTPReply.isPositiveCompletion(ftpClient.getReplyCode()));
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE, FTP.BINARY_FILE_TYPE);
            ftpClient.setFileTransferMode(FTP.BINARY_FILE_TYPE);
            FTPFile[] dirs =  ftpClient.listFiles("/");
            System.out.println("i = " + dirs.length);
            FTPFile[] ftpFiles = ftpClient.listFiles("/reports/board/");
            System.out.println(ftpFiles.length);
            for (FTPFile ftpFile : ftpFiles) {
                System.out.println(ftpFile);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ( ftpClient.isConnected() ) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Path unzip(Path path) throws IOException {
        Path unzipPath = Paths.get(path.toString(),"unzip");
        File unzipDir = unzipPath.toFile();
        if ( !unzipDir.exists() ) unzipDir.mkdir();
        else {
            FileUtils.cleanDirectory(unzipDir);
        }
        for (File file : path.toFile().listFiles()) {
            if ( file.isDirectory() ) continue;
            Utils.unZipIt(file.toString(),unzipDir.toString());
        }
        return Paths.get(unzipDir.toString());
    }

    public Multimap<String, Board> parse(Path unzipPath) throws FileNotFoundException {
        File[] files = unzipPath.toFile().listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.contains("Q4");
            }
        });
        Multimap<String, Board> boards = ArrayListMultimap.create();
        for (File file : files) {
            boards.putAll(deserialize(file));
        }
        return boards;
    }


    public static void main(String[] args) throws IOException {
        BoardService boardService = new BoardService();
        /*
        File file = new File("/Users/lameroot/Downloads/small-letters/rts-board/150323_4/150323Q4.txt");
        Multimap<String, Board> boards = boardService.deserialize(file);
        for (Map.Entry<String, Collection<Board>> entry : boards.asMap().entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue().size());
        }
        */



        //boardService.downloadFiles();
        Path newPath = boardService.unzip(Paths.get("/Users/lameroot/Downloads/small-letters/rts-board/ftp_data/all2today/"));
        Multimap<String, Board> boards1 = boardService.parse(newPath);
        for (Map.Entry<String, Collection<Board>> entry : boards1.asMap().entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue().size());
        }
        Collection<Board> bgesp = boards1.get("mutf");
        for (Board board : bgesp) {
            System.out.println(board.getMoment() + " : " + board.getBid() + " / " + board.getAsk());
        }

    }
}
