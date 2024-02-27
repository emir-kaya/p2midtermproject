package midtermProject;

import java.util.Scanner;

public class midtermProject {

    public static void main(String[] args) {
        GameObject[][] gameObjectArray = new GameObject[3][3];

        String userInput = "";//Kullanıcıdan alınacak grid elemanları

        Scanner sc = new Scanner(System.in);
        System.out.println("Lütfen obje listesini girin: ");
        userInput = sc.nextLine();

        int inputIndex = 0;//Burada string içerisindeki nesneleri iki boyutlu diziye yerleştiriyor
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char c = userInput.charAt(inputIndex);//user inputun içerisindeki elemanlara ulaşıyor
                if (c == 'C') {
                    gameObjectArray[i][j] = new Cup();
                }
                if (c == 'B') {
                    gameObjectArray[i][j] = new Ball();
                }
                if (c == 'H') {
                    gameObjectArray[i][j] = new Hat();
                }
                if (c == 'P') {
                    gameObjectArray[i][j] = new Pen();
                }
                if (c == 'U') {
                    gameObjectArray[i][j] = new Unknown();
                }

                inputIndex++;//inputu her seferinde bir arttırarak bir sonraki elemana gidiyor
            }
        }

        writeMatrix(gameObjectArray);//matrisi yazdırıyor

        while (!isAllObjectOpened(gameObjectArray)) {//Eğer nesnelerin hepsi açık olmadığı sürece openIndex metodunu çağırıyor
            openIndex(gameObjectArray);
        }

        closeAllObject(gameObjectArray);//Hepsi açıldıktan matrisi kapatıyor

        System.out.println("Tahmininizi Girin:");
        String userFinalInput = sc.nextLine();// tahmini userFinalInput içerisine atıyor (kullanıcıdan alıyor)
        int score = 0;
        for (int i = 0; i < userInput.length(); i++) {//burada tahminle ilk başta girilen grid eşitse skoru arttırıyor
            if (userInput.charAt(i) == userFinalInput.charAt(i)) {
                score++;
            }
        }

        System.out.println("------------");
        System.out.println("GERÇEK DEĞER: " + userInput);
        System.out.println("SİZİN TAHMİNİNİZ: " + userFinalInput);
        System.out.println("SKORUNUZ: " + score);
        if (score >= 4) {
            System.out.println("BAŞARILI !");
        } else {
            System.out.println("BAŞARISIZ !");
        }
    }

    public static void writeMatrix(GameObject[][] array) {//Matrisi yazdırıyor
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {//Burada whoAmI metoduyla gridi yazdırıyor
                if (array[i][j].getIsOpened()) {//Eğer grid açıksa whoAmI değilse X yazdırıyor
                    System.out.print(array[i][j].whoAmI() + " ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println("");
        }
    }

    public static void openIndex(GameObject[][] array) {//Seçilen satır sütunu açıyor
        int userInput = 0;
        Scanner sc = new Scanner(System.in);//Burada kullanıcıdan satır sütunu alıyor
        System.out.println("Lütfen İndex Giriniz: ");
        userInput = sc.nextInt();
        int row = userInput / 10;//kullanıcıdan satır ve sütunu birleşik olarak aldığı için önce onlar sonra birler basamağına ulaşıyor
        int col = userInput % 10;

        if (array[row][col].getIsOpened()) {//Eğer açılması istenilen eleman açıksa "Zaten açık" olduğunu belirtiyor
            System.out.println("Zaten açık");
        } else {
            array[row][col].setIsOpened(true);//Eğer açık değilse (boolean)setIsOpened metodunu kullanarak elemanı açıyor
            writeMatrix(array);//matrisi tekrar yazdırıyor
        }
    }

    public static boolean isAllObjectOpened(GameObject[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {//Burada matrisin tüm elemanlarına ulaşıp hepsi açık değilse boolean false
                if (!array[i][j].getIsOpened()) {//Eğer açıksa boolean true oluyor
                    return false;
                }
            }
        }

        return true;
    }

    public static void closeAllObject(GameObject[][] array) {
        String userInput = "";
        Scanner sc = new Scanner(System.in);

        System.out.println("Matrisi tekrar kapatmak için 'Evet' yazınız:");
        userInput = sc.nextLine();
        if (userInput.equals("Evet")) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[0].length; j++) {
                    if (array[i][j].getIsOpened()) {
                        array[i][j].setIsOpened(false);//Burada gridin tüm elemanlarına ulaşıp hepsini kapatıyor
                    }
                }
            }

            writeMatrix(array);//Matrisi tekrar yazdırıyor
        } else {
            closeAllObject(array);//Eğer kullanıcı evet girmezse metodu baştan başlatıyor
        }
    }
}

interface GameObject {

    boolean getIsOpened();//Açık olup olmadığını kontrol ediypr

    void setIsOpened(boolean value);//Açıp kapatmayı sağlıyor

    String whoAmI();//Elemanların baş harflerine ulaşıyor
}

class Cup implements GameObject {

    public boolean isOpened = false;//İlk başta hepsi kapalı olduğu için false

    public String whoAmI() {
        return "C";
    }

    public boolean getIsOpened() {
        return isOpened;
    }

    public void setIsOpened(boolean value) {
        isOpened = value;
    }
}

class Ball implements GameObject {

    public boolean isOpened = false;

    public String whoAmI() {
        return "B";
    }

    public boolean getIsOpened() {
        return isOpened;
    }

    public void setIsOpened(boolean value) {
        isOpened = value;
    }
}

class Hat implements GameObject {

    public boolean isOpened = false;

    public String whoAmI() {
        return "H";
    }

    public boolean getIsOpened() {
        return isOpened;
    }

    public void setIsOpened(boolean value) {
        isOpened = value;
    }
}

class Pen implements GameObject {

    public boolean isOpened = false;

    public String whoAmI() {
        return "P";
    }

    public boolean getIsOpened() {
        return isOpened;
    }

    public void setIsOpened(boolean value) {
        isOpened = value;
    }
}

class Unknown implements GameObject {

    public boolean isOpened = false;

    public String whoAmI() {
        return "U";
    }

    public boolean getIsOpened() {
        return isOpened;
    }

    public void setIsOpened(boolean value) {
        isOpened = value;
    }
}

