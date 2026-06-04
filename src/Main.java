import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MovieManagement movieManagement = new MovieManagement();

        while(true){
            System.out.println("\n=== CHUONG TRINH QUAN LY PHIM ===");
            System.out.println("1. Them phim");
            System.out.println("2. Liet ke phim");
            System.out.println("3. Sua phim");
            System.out.println("4. Xoa phim");
            System.out.println("0. Thoat");
            System.out.println("Lua chon cua ban: ");
            int choice = -1;
            try{
                choice = Integer.parseInt(scanner.nextLine().trim());
            }catch(NumberFormatException e){
                System.err.println("=> Loi: Vui long nhap so hop le!");
                continue;
            }

            try{
                switch(choice){
                    case 1:
                        System.out.println("Nhap ten phim: ");
                        String title = scanner.nextLine();
                        System.out.println("Nhap dao dien: ");
                        String director = scanner.nextLine();
                        System.out.println("Nhap nam phat hanh: ");
                        int year = Integer.parseInt(scanner.nextLine().trim());
                        movieManagement.addMovie(title, director, year);
                        break;
                    case 2:
                        movieManagement.listMovies();
                        break;
                    case 3:
                        System.out.println("Nhap ID phim can sua: ");
                        int idUpdate = Integer.parseInt(scanner.nextLine().trim());
                        System.out.println("Nhap ten phim moi: ");
                        String newTitle = scanner.nextLine();
                        System.out.println("Nhap dao dien moi: ");
                        String newDirector = scanner.nextLine();
                        System.out.println("Nhap nam phat hanh moi: ");
                        int newYear = Integer.parseInt(scanner.nextLine().trim());
                        movieManagement.updateMovie(idUpdate, newTitle, newDirector, newYear);
                        break;
                    case 4:
                        System.out.println("Nhap ID phim can xoa: ");
                        int idDelete = Integer.parseInt(scanner.nextLine().trim());
                        movieManagement.deleteMovie(idDelete);
                        break;
                    case 0:
                        System.out.println("=> Da thoat chuong trinh.");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("=> Lua chon khong hop le. Vui long chon lai!");
                }
            }catch(NumberFormatException e){
                System.err.println("Loi: ID va Nam phat hanh bat buoc phai la chu so!");
            }catch (IllegalArgumentException e){
                System.err.println("Loi: " + e.getMessage());
            }catch (Exception e){
                System.err.println("Loi he thong: " + e.getMessage());
            }
        }
    }
}