import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import static com.project.PdfMerge.mergePdfFiles;


class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> filePath = new ArrayList<>();

        try{
            //Prepare input pdf file list as list of input stream.
            List<InputStream> inputPdfList = new ArrayList<InputStream>();

            //to do next:
            //Add a scanner object (or args) and enter to the list.

            System.out.println("How many files to merge? ");
            int fileNumber = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < fileNumber; i++){
                System.out.println("Enter file: ");
                String file1 = scanner.nextLine();
                filePath.add(file1);
            }

            for (String list : filePath){
                inputPdfList.add(new FileInputStream(list));
            }


            System.out.println("Please enter merged file name:");
            String output = scanner.next();

            //prepare output stream for merged pdf file.
            OutputStream outputStream = new FileOutputStream(output);

            // call method to merge pdf files.
            mergePdfFiles(inputPdfList, outputStream);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}