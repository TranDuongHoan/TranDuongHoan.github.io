package com.example.librarymanagementspring.logic;

import com.example.librarymanagementspring.entity.Book;
import com.example.librarymanagementspring.entity.Reader;
import com.example.librarymanagementspring.entity.RentalManagement;
import com.example.librarymanagementspring.entity.RentalManagementDetail;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RentalLogicManagement {

    List<RentalManagement> rentalManagements = new ArrayList<>();
    BookLogic bookLogic;
    ReaderLogic readerLogic;


    public void inputNewRental() {
        if(!readerLogic.readerIsNotEmpty() || !bookLogic.bookIsNotEmpty()) {
            System.out.println("Không có dữ liệu về người đọc hoặc đầu sách, vui lòng tạo dữ liệu:");
            return;
        }

        System.out.println("Nhập số người mượn sách:");
        int readerNumber = new Scanner(System.in).nextInt();
        for (int i = 0; i < readerNumber; i++) {
            System.out.println("Nhập thông tin cho người đọc thứ" + (i + 1) + "muốn mượn sách.");
            System.out.println("Nhập ID người đọc:");
            int readerId;
            Reader reader = null;
            do{
                readerId = new Scanner(System.in).nextInt();
                for (int j = 0; j < readerLogic.getReaders().size(); j++) {
                    if (readerLogic.getReaders().get(j).getId() == readerId) {
                        reader = readerLogic.getReaders().get(j);
                        break;
                    }
                }
                if (reader != null) {
                    break;
                }
                System.out.println("Không tồn tại ID người đọc vừa nhập, xin vui lòng nhập lại: ");
            }while (true);

            System.out.println("Người đọc mượn bao nhiêu đầu sách: ");
            int book = new Scanner(System.in).nextInt();

            List<RentalManagementDetail> details = new ArrayList<>();

            int totalBook = 0;
            for (int j = 0; j < book; j++) {
                System.out.println("Đầu sách thứ " + (j + 1) + "mà người đọc muốn mượn là đầu sách nào: ");
                int bookId;
                Book book = null;
                do{
                    bookId = new Scanner(System.in).nextInt();
                    for (int k = 0; k < bookLogic.getBooks().size(); k++) {
                        if (bookLogic.getBooks().get(k).getId() == bookId) {
                            book = bookLogic.getBooks().get(k);
                            break;
                        }
                    }
                    if (book != null) {
                        break;
                    }
                    System.out.println("Không tồn tại đầu sách có ID vừa nhập, xin vui lòng nhập lại: ");
                }while (true);

                System.out.println("Đầu sách này người đọc muốn mượn bao nhiêu cuốn sách:");
                int bookNumber;
                do {
                    bookNumber = new Scanner(System.in).nextInt();
                    if (bookNumber < 1 || bookNumber > 3) {
                        System.out.println("Số lượng sách mượn không quá 3 cuốn.");
                        continue;
                    }
                    int temp = totalBook;
                    temp+=bookNumber * book.getTotalBook();
                    if (temp < 5){
                        System.out.println("Nếu số đầu sách vừa nhập quá 5, vui lòng nhập lại: ");
                        continue;
                    }
                    break;
                } while (true);

                RentalManagementDetail rentalManagementDetail = new RentalManagementDetail(book, bookNumber);

                details.add(rentalManagementDetail);
                totalBook += bookNumber * book.getTotalBook();
            }

            RentalManagement rentalManagement = new RentalManagement(reader, details, totalBook);
            rentalManagements.add(rentalManagement);
        }
    }

    public void showRental() {
        System.out.println(rentalManagements);
    }


    public void sortByReaderName() {
        if (isEmptyRental()){
            System.out.println("Chưa có dự liệu người đọc, vui lòng nhập dữ liệu trước khi sắp xếp");
            return;
        }

        for (int i = 0; i < rentalManagements.size() -1; i++){
            for (int j = i+1; j < rentalManagements.size(); j++){
                if(rentalManagements.get(i).getReader().getName().compareToIgnoreCase(rentalManagements.get(j).getReader().getName()) > 0){
                    RentalManagement temp = rentalManagements.get(i);
                    rentalManagements.set(i, rentalManagements.get(j));
                    rentalManagements.set(j,temp);
                }
            }
        }
        showRental();
    }

    public void sortByTotalBook() {
        if (isEmptyRental()){
            System.out.println("Chưa có dự liệu người đọc, vui lòng nhập dữ liệu trước khi sắp xếp");
            return;
        }
        for(int i = 0; i< rentalManagements.size(); i++){
            for (int j = 0; j < rentalManagements.get(i).getDetails().size(); j++){
                for(int k = j + 1; k < rentalManagements.get(i).getDetails().size(); k++){
                    if(rentalManagements.get(i).getDetails().get(j).getBook().getTotalBook() > rentalManagements.get(i).getDetails().get(j),){
                        RentalManagementDetail temp = rentalManagements.get(i).getDetails().get(k);
                        rentalManagements.get(i).getDetails().set(j,rentalManagements.get(i).getDetails().get(k));
                        rentalManagements.get(i).getDetails().set(k, temp);
                    }
                }
            }
        }
        showRental();
    }

    private boolean isEmptyRental(){
        for (int i = 0; i < rentalManagements.size(); i++){
            if (rentalManagements.get(i) != null){
                return false;
            }
        }
        return true;
    }
}
