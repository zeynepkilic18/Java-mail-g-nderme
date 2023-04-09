import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    static String ad,soyad;
    static String mail;
    static int tusla,islem;
    //Dışardan değer alabilmek için kullanırız.
    static String metin;
    //Mail içeriğini metin değişkeninde tutarız.

    static Scanner scanner=new Scanner(System.in);
    //Dışardan değer alabilmek için kullanırız.

    public static void üye() {
        Scanner scanner=new Scanner(System.in);
             /*
             Üye fonksiyonu isim,soyisim,mail değerlerini bir kez yazıp birden çok
             kullanmamızda kolaylık sağlar.
             */
        System.out.println("Lütfen ismi,soyismi,maili giriniz(Her bir giristen sonra enter yaparak): ");
        ad=scanner.nextLine();
        soyad=scanner.nextLine();
        mail=scanner.nextLine();


    }


    public static void main(String[] args) throws Exception {
        Scanner scanner=new Scanner(System.in);
        Mail_Gönder mail2=new Mail_Gönder();
        //Mail_Gönder classındaki bilgileri Main class içinde kullanabilmek için mail2 nesnesini tanımladık.
        System.out.println('\n'+"*****Mail gönderme uygulamasina hos geldiniz.*****"+'\n');


        File file1=new File("elitÜyeler.txt");
        //Dosyada bir sorun yok ise "elitÜyeler.txt" isimli 1.dosyayı açıyoruz.
        if (file1.exists())
        {
            System.out.println("-Belirtilen dizin yolunda, belirtmis oldugunuz dosya zaten mevcut."+'\n');

        }
        else
        {
            try
            {
                file1.createNewFile();
                System.out.println("-Basarılı bir şekilde, dizin üzerinde belirtmis oldugunuz isimde dosya oluşturuldu.\n");
            }
            catch (IOException e)
            {
                System.out.println("-Dosya hatası!!"+'\n'+"Hata sebebi: "+ e.getMessage());
            }
        }


        //Dosya yazılma işlemleri yapılıyor.
        FileWriter f1writer=new FileWriter(file1,false);
        BufferedWriter b1writer=new BufferedWriter(f1writer);



        File file2=new File("genelÜyeler.txt");
        //Dosyada bir sorun yok ise "genelÜyeler.txt" isimli 2.dosyayı açıyoruz.
        if (file2.exists())
        {
            System.out.println("-Belirtilen dizin yolunda, belirtmis oldugunuz dosya zaten mevcut."+'\n');

        }
        else
        {
            try
            {
                file2.createNewFile();
                System.out.println("-Basarılı bir şekilde, dizin üzerinde belirtmis oldugunuz isimde dosya oluşturuldu.\n");
            }
            catch (IOException e)
            {
                System.out.println("-Dosya hatası!!"+'\n'+"Hata sebebi: "+ e.getMessage());
            }
        }


        //Dosya yazılma işlemleri yapılıyor.
        FileWriter f2writer=new FileWriter(file2,true);
        BufferedWriter b2writer=new BufferedWriter(f2writer);


        do {
            System.out.println("Lütfen yapmak istediginiz islemi tuslayin(1,2,3): ");
            System.out.println("1-Elit üye ekleme"+'\n'+"2-Genel üye ekleme"+'\n'+"3-Mail gönderme");
            tusla=scanner.nextInt();

            if (tusla==1)
            {
                System.out.println("-Elit üye girisi yapiliyor-"+'\t');
                for (int i=0;i<3;i++)
                {
                    üye();
                    //Üye fonksiyonunu çağırıyoruz
                    b1writer.write(ad);
                    b1writer.write("\t");
                    b1writer.write(soyad);
                    b1writer.write("\t");
                    b1writer.write(mail);
                    b1writer.write("\n");
                    /*
                    3 adet elit üye tanımlamamız istendiği için 3 elit üye tanımlayıp bu 3 elit üyenin bilgilerini
                    dosyaya kaydettik.
                    */

                }
                //Dosyayı kapattık.
                b1writer.close();

            }

            else if (tusla==2)
            {
                System.out.println("-Genel üye girisi yapiliyor-"+'\n');
                üye();
                //Üye fonksiyonunu çağırıyoruz
                b2writer.write(ad);
                b2writer.write("\t");
                b2writer.write(soyad);
                b2writer.write("\t");
                b2writer.write(mail);
                b2writer.write("\n");
                b2writer.close();
            }

            else if (tusla==3) {
                System.out.println("-Mail gönderme yapiliyor-"+'\t');
                mail2.mail_gönder();
                //Tanımladığımız mail2 nesnesinden mail gönder fonksiyonumuzu çağırıyoruz

            }
            System.out.println("İsleme devam etmek istiyorsaniz 1'i, islemi bitirmek istiyorsaniz 2'yi tuslayin");
            islem=scanner.nextInt();

        }while (islem==1);
        //do while ile islem 1 girildikçe tekrardan işlemlerimizi döndürüyoruz.


        System.out.println("-Dosya adi: " + file1.getName()+'\n'+"-Dosya dizin yolu: "+ file1.getPath());
        System.out.println("-Dosya okunabilirlik durumu: " + file1.canRead()+'\n'+'\n');
        System.out.println("-Dosya adi: " + file2.getName()+'\n'+"-Dosya dizin yolu: "+ file2.getPath());
        System.out.println("-Dosya okunabilirlik durumu: " + file2.canRead()+'\n'+'\n');


        FileReader f1reader=new FileReader(file1);
        String satır1;
        //1.Dosyayı okurken satır satır okuması için String satır1 oluşturduk.
        BufferedReader b1reader=new BufferedReader(f1reader);

        while ((satır1=b1reader.readLine())!=null){
            System.out.println("-Elit üye dosyasi okundu icinde yazanlar:");
            System.out.println(satır1);
        }
        //Dosyada herhangi bir sorun yoksa dosyayı okuduk.



        FileReader f2reader=new FileReader(file2);
        String satır2;
        //2.Dosyayı okurken satır satır okuması için String satır2 oluşturduk.
        BufferedReader b2reader=new BufferedReader(f2reader);

        while ((satır2=b2reader.readLine())!=null){
            System.out.println('\n'+"-Genel üye dosyasi okundu icinde yazanlar: ");
            System.out.println(satır2);
        }
        //Dosyada herhangi bir sorun yoksa dosyayı okuduk.

        Java_Mail.sendMail(mail);
        //Mail gönderme kodlarını çağırdık dosyadan aldığımız maili paramete olarak atayarak


    }
}




/*Kalıtım kullanıp Mail_Gönder'i Main'in alt sınıfı olarak tanımladık böylece Main class'ındaki
değişkenleri(tusla) kullanabileceğiz */
class Mail_Gönder extends Main {

    public static void mail_gönder() throws Exception {
        System.out.println("Lütfen yapmak istediginiz islemi tuslayin(1,2,3): ");
        System.out.println("1-Elit üyelere mail"+'\n'+"2-Genel üyelere mail"+'\n'+"3-Tüm üyelere mail");
        tusla=scanner.nextInt();

        switch (tusla)
        {
            case 1:
                System.out.println("-Elit üyelere mail atiliyor-"+'\t');
                System.out.println("Göndermek istediginiz mail icerigini girin: ");
                scanner.nextLine();
                metin=scanner.nextLine();
                Java_Mail.sendMail(mail);
                break;

            case 2:
                System.out.println("-Genel üyelere mail atiliyor-"+'\t');
                System.out.println("Göndermek istediginiz mail icerigini girin: ");
                scanner.nextLine();
                metin=scanner.nextLine();
                Java_Mail.sendMail(mail);
                break;

            case 3:
                System.out.println("-Tüm üyelere mail atiliyor-"+'\t');
                System.out.println("Göndermek istediginiz mail icerigini girin: ");
                scanner.nextLine();
                metin=scanner.nextLine();
                Java_Mail.sendMail(mail);
                break;

        }

    }


}

/*Kalıtım kullanıp Mail_Gönder'i Main'in alt sınıfı olarak tanımladık böylece Main class'ındaki
değişkenleri(metin) kullanabileceğiz */
class Java_Mail extends Main{
    public static void sendMail(String recepient) throws Exception {
        System.out.println("Mesaj göndermeye hazirlaniliyor");
        Properties properties=new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        String myAccountEmail="mailyolla123@gmail.com";
        String password="mwhwlykpyhhrhuag";
        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        Message message= prepareMessage(session, myAccountEmail, recepient) ;
        Transport.send(message);
        System.out.println("mail basariyla gönderildi");
    }
    private static Message prepareMessage(Session session, String myAccountEmail, String recepient){
        try {
            Message message= new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("VizeProje");
            message.setText(metin);
            return message;
        } catch (Exception ex) {
            Logger.getLogger(Java_Mail.class.getName()).log(Level.SEVERE, null,ex);
        }
        return null;
    }
}

