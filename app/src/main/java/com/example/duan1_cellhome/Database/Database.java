package com.example.duan1_cellhome.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.duan1_cellhome.R;

public class Database extends SQLiteOpenHelper {
    public Database(Context context) {
        super(context, "BDSDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {



        String sql="CREATE TABLE  ThanhVien(maTV TEXT PRIMARY KEY NOT NULL, hoTen TEXT NOT NULL, tenTK TEXT NOT NULL, matKhau TEXT, namSinh TEXT, soDT INTEGER, vaiTro INTEGER)";
        db.execSQL(sql);
        sql="CREATE TABLE  NhaDat(maNhaDat TEXT PRIMARY KEY NOT NULL, tenGT TEXT NOT NULL, hinh BLOB REFERENCES Hinh(hinh), tinhThanh TEXT,ngayDang DATE, diaChi TEXT, giaTien INTEGER, dienTich TEXT, moTa TEXT, loaiNha INTEGER)";
        db.execSQL(sql);
        sql="CREATE TABLE  Hinh(maHinh TEXT PRIMARY KEY,maNhaDat TEXT REFERENCES NhaDat(maNhaDat),hinh BLOB )";
        db.execSQL(sql);
        sql="CREATE TABLE  DonHang(maDonHang TEXT PRIMARY KEY NOT NULL, " +
                "maTV TEXT REFERENCES ThanhVien(maTV)," +
                "maNhaDat TEXT REFERENCES NhaDat(maNhaDat)," +
                "soDTNM INTEGER," +
                "tenGTND TEXT NOT NULL," +
                "hinhND BLOB," +
                "diaChiND TEXT NOT NULL, giaTienND INTEGER, trangThai INTEGER, ngay DATE)";
        db.execSQL(sql);

        db.execSQL("INSERT INTO ThanhVien VALUES('abc','Hồ Hoàng Phúc','phuc1','123','2002',0198139131,1)");
        db.execSQL("INSERT INTO ThanhVien VALUES('abc1','Lê Văn Tài','tai1','123','2002',0198139131,1)");
        db.execSQL("INSERT INTO ThanhVien VALUES('abc2','Phan Thanh Duy','duy1','123','2002',0198139131,1)");
        db.execSQL("INSERT INTO ThanhVien VALUES('1','Khuất Đại An','admin','123','1999',0338779199,0)");

        db.execSQL("INSERT INTO NhaDat VALUES('1','Biệt thự ngay trung tâm hành chính, gía rất rẻ',null,'Lâm Đồng','30/11/2021','Lạc Long Quân, Lộc Quảng, Huyện Bảo Lâm, Lâm Đồng',55000,'256m2','Đường nhựa 8,4m, vỉa hè mỗi bên 1,2m.\n" +
                "Ngay khu dân cư đông đúc.\n" +
                "Cách nút giao cao tốc Dầu Giây - Liên Khương 3km.\n" +
                "View đồi thoáng mát, không khí trong lành, mát mẻ quanh năm.',0)");

        db.execSQL("INSERT INTO NhaDat VALUES('2','Nền liền kề Điện máy xanh MT Tỉnh lộ 10',null,'Hồ Chí Minh','20/11/2021','Tỉnh Lộ 10, Xã Phạm Văn Hai, Huyện Bình Chánh, TPHCM',72000,'105m2','Mặt tiền Tỉnh Lộ 10, liền kề với điện máy xanh tỉnh lộ 10.\n" +
                "đất cách bệnh viện chợ rẫy 2 khoản 1km,\n" +
                "về BX Miền tây 5 phút,\n" +
                "Ngay Chi cục thuế, UBND Quận, bệnh viện Triều An, bệnh viện Quốc Tế City...',1)");

        db.execSQL("INSERT INTO NhaDat VALUES('3','Bán Biệt Thự Cao Cấp Khu dân cư Cityland',null,'Hồ Chí Minh','29/11/2021','Phường 10 - Quận Gò Vấp - Thành phố Hồ Chí Minh',759000,'100m2','Nhà xây 4 lầu đẹp, hoàn thiện, full nội thất.\n" +
                "Tọa lạc tại trục đường số 08, mặt đường 8m, lề 2m.\n" +
                "3 tầng, 5 PN, 4 WC.\n" +
                "An ninh 24/24, cây xanh mát mẻ, gần ngay siêu thị E mart, trường quốc tế Việt - Úc.',0)");

        db.execSQL("INSERT INTO NhaDat VALUES('4','Bán Lô Đất Mặt Tiền Hàng Hiếm Sát Ql 55',null,'Bình Thuận','27/11/2021','Quốc Lộ 55, Xã Tân Xuân, Huyện Hàm Tân, Bình Thuận',260000,'4000m2','Là lô 3 mặt tiền hàng đẹp, siêu hiếm tại Tân Xuân.\n" +
                "Đường rộng 6m xe hơi vi vu thoải mái.\n" +
                "Cách quốc lộ 55 chỉ 200m.\n" +
                "Gần ủy ban,chợ , ngay trung tâm thuận tiện cho việc đi lại.',1)");

        db.execSQL("INSERT INTO NhaDat VALUES('5','Bán Nhà Ngay Mặt Tiền Của Khu Đô Thị Sầm Uất',null,'Bình Phước','15/11/2021','Đồng Khởi, Xã Minh Hưng, Huyện Chơn Thành, Bình Phước',34000,'145m2','Tiện ích đạt chuẩn khu đô thị loại 4:\n" +
                "Trường học, công viên, trung tâm thương mại, trạm y tế, cây xăng, ngân hàng đầy đủ.\n" +
                "Đường 13 mét , vỉa hè ốp lát gạch bông.\n" +
                "Gần ủy ban,chợ , ngay trung tâm thuận tiện cho việc đi lại.',0)");

        db.execSQL("INSERT INTO NhaDat VALUES('6','Bán nhà phố liền kề Đường Phan Huy Ích',null,'Hồ Chí Minh','10/11/2021','Phan Huy Ích, Phường 14, Quận Gò Vấp, TPHCM',315000,'218m2','1 trệt, 1 lửng, 3 lầu. \n" +
                "5 phòng ngủ, 7 WC, phòng thờ, sân thượng trước sau.\n" +
                "Nhà hướng tây, Chủ nhà tặng full nội thất.',0)");

        db.execSQL("INSERT INTO NhaDat VALUES('7','Bán Nhanh Nhà 3 Tầng, Không Còn Căn Thứ 2 Đẹp Hơn ',null,'Hải Phòng','10/11/2021','Máng nước, Xã An Đồng, Huyện An Dương, Hải Phòng',76000,'140m2','Ra chợ Vĩnh Khê, trường tiểu học An Đồng chưa đầy 5p đi xe. \n" +
                "Ngõ to ô tô vào tận cửa, đường thông 2 lối đi.\n" +
                "Chủ để lại nóng lạnh, tủ quần áo, tủ bếp.',0)");

        db.execSQL("INSERT INTO NhaDat VALUES('8','Biệt thự mini liền kề 6x14m Huỳnh Tấn Phát ',null,'Hồ Chí Minh','18/11/2021','Huỳnh Tấn Phát, Phường Phú Mỹ, Quận 7, TPHCM',270000,'80m2','Khu dân cư đường 08m, bảo vệ 24/24 đường Huỳnh Tấn Phát. \n" +
                "Hướng Đông Bắc, đón ánh nắng buổi sáng, buổi chiều khuất ánh mặt trời, mát mẻ cả ngày.\n" +
                "Đã có sẵn nội thất, chỉ cần dọn vào ở ngay.',0)");

        db.execSQL("INSERT INTO NhaDat VALUES('9','Biệt thự View Vịnh Hạ Long inbox để có giá cực mềm ',null,'Quảng Ninh','29/11/2021','Vườn Đào, Bãi Cháy, TP. Hạ Long, Quảng Ninh',7850000,'320m2','Trên đồi Hải quân View toàn bộ Vịnh HẠ Long. \n" +
                "Cách Đảo Tuần Châu 14km, Cách Ga Hạ Long 6,5km. \n" +
                "Cách Bảo tàng Quảng Ninh 7km. Có gym, spa, cafe, và một số tiện ích nội khu đẳng cấp',0)");

        db.execSQL("INSERT INTO NhaDat VALUES('10','Đất Đẹp Đường 12m Hẻm 1806 Huỳnh Tấn Phát ',null,'Hồ Chí Minh','29/11/2021','Huỳnh Tấn Phát, Thị Trấn Nhà Bè, Huyện Nhà Bè, TPHCM',180000,'76m2','Đất Đẹp Đường 12m Hẻm 1806 Huỳnh Tấn Phát Nhà Bè. \n" +
                "Cách trợ, trường học, siêu thị mini 5p đi đường',1)");

        db.execSQL("INSERT INTO NhaDat VALUES('11','Đất dự án khu đô thị khu đô thị ân phú ',null,'Đắk Lắk','23/11/2021','Hà Huy Tập, Phường Tân An, TP. Buôn Ma Thuột, Đắk Lắk',87000,'100m2','Đất nền SỔ ĐỎ KĐT Ân Phú tâm điểm đầu tư tại Buôn Ma Thuột. \n" +
                "Tọa lạc trên trục Hà Huy Tập kết nối xuyên suốt Tp Buôn Ma Thuột. \n" +
                "Là một trong 3 dự án NGHÌN TỶ tại khu vực',1)");

        db.execSQL("INSERT INTO NhaDat VALUES('12','Đất góc 2 Mặt Tiền đường Tỉnh lộ 7 Huyện Củ Chi ',null,'Hồ Chí Minh','23/11/2021','Tỉnh Lộ 7, Xã Phước Thạnh, Huyện Củ Chi, TPHCM',87000,'1071,9m2',' góc 2 mặt tiền đường đất lộ giới 12m xe hơi vào ra thoải mái. \n" +
                "Đất đã san lấp bằng phẳng đẹp, thích hợp xây biệt thự vườn nghỉ dưỡng \n" +
                "quá gần gũi với thiên nhiên nhưng lại gần khu dân cư hiện hữu',1)");

        db.execSQL("INSERT INTO NhaDat VALUES('13','Đất vườn mít, vườn sầu riêng view Hồ Trị An, Đồng Nai ',null,'Đồng Nai','23/11/2021','Đường số 101, Xã La Ngà, Huyện Định Quán, Đồng Nai',48000,'1120m2','Gần sông La Ngà - Sông Đồng Nai nên khí hậu mát mẻ. \n" +
                "Cách TP HCM chỉ 1h30 phút đi xe. Cách Khu du lịch suối mơ 3km. \n" +
                "Khu dân cư đông đúc, ra chợ, trường học thcs, Thpt chỉ 5 phút di chuyển.',1)");

        db.execSQL("INSERT INTO NhaDat VALUES('14','Lô đất siêu đẹp ngay chợ Chánh Lưu ',null,'Bình Dương','23/10/2021','ĐT 741, Xã Chánh Phú Hòa, Thị Xã Bến Cát, Bình Dương',165000,'2645m2','mặt tiền 11,7m dài 122m, nở hậu 42m. \n" +
                "Cách chợ chánh lưu 2km, Ngay đầu đường thông Mỹ Phước Tân Vạn. \n" +
                "Sổ sẵn, bao công chứng sang tên trong ngày.',1)");

        db.execSQL("INSERT INTO NhaDat VALUES('15','Nền đất trả lãi ngân hàng do dịch bệnh kéo dài ',null,'Hồ Chí Minh','29/10/2021','Trần Văn Giàu, Xã Lê Minh Xuân, Huyện Bình Chánh, TPHCM',65000,'98m2','Cách khu Tên Lửa Aeon MALL Bình Tân đi xuống 4km. \n" +
                "Từ trục đường Tỉnh Lộ 10 vào 300m. \n" +
                "Thuận tiện kinh doanh, buôn bán, mua ở lâu dài.!',1)");

        db.execSQL("INSERT INTO NhaDat VALUES('16','Nền đất liền kề 10x26 ,Mặt Tiền Tỉnh lộ 10 ',null,'Hồ Chí Minh','19/10/2021','Tỉnh Lộ 10, Xã Phạm Văn Hai, Huyện Bình Chánh, TPHCM',85000,'130m2','Nằm trên mặt tiền đường tỉnh lộ 10 liền kề điện máy xanh. \n" +
                "Đất nằm trong khu chợ dân cư buôn bán đông đúc. \n" +
                "Gần trường học và liền kề với cụm khu công nghiệp POUYUEN.',1)");

        db.execSQL("INSERT INTO NhaDat VALUES('17','Nhà Mặt Tiền Dương Công Khi KDC hiện hữu ',null,'Hồ Chí Minh','9/10/2021','Dương Công Khi, Xã Xuân Thới Thượng, Huyện Hóc Môn, TPHCM',1195000,'1000m2','Cách đài truyền thống ngã 3 giồng 200m. \n" +
                "Chuyển nốt thổ cư vô tư, xây dựng tự do. \n" +
                "Đường nhựa lớn xe công song song, tiện kdbb,....',0)");

        db.execSQL("INSERT INTO NhaDat VALUES('18','Nhà Mặt Tiền Đường Số 1979 Huỳnh Tấn Phát ',null,'Hồ Chí Minh','9/10/2021','Đường Số 08 Huỳnh Thị Đồng,Nhà Bè ',257000,'52m2','Cạnh quận 7 cách Phú Mỹ Hưng 5p. \n" +
                "Nhà được Deco thiết kế tinh tế đến từng chi tiết, đường 9m có lề, xe hơi vào tận nhà. \n" +
                "Nhà gần UBND, công an thị trấn, an ninh rất văn minh',0)");

        db.execSQL("INSERT INTO NhaDat VALUES('19','Nhà phố biệt lập an ninh 2424 chỉ cần 1,6 tỷ ',null,'Bình Dương','18/10/2021','ân Phước Khánh 10, phường Tân Phước Khánh, Thị xã Tân Uyên, Bình Dương ',70000,'210m2','Đón sóng đầu tư khi Tân Uyên lên đô thị loại II. \n" +
                "Không gian sống xanh, bình yên, an ninh, hiện đại, thông minh và đẳng cấp. \n" +
                "Gần phòng gym, yoga, siêu thị mini,hồ bơi',0)");

        db.execSQL("INSERT INTO NhaDat VALUES('20','Nhà phố tân cổ điển đường vào cho xe hơi ',null,'Hồ Chí Minh','18/10/2021','Phố Tân Cổ Điển Hẻm 1806 Huỳnh Tấn Phát Nhà Bè ',240000,'59m2','Sân trước để xe thoải mái xe hơi đậu tận nhà. \n" +
                "Sân sau giếng trời thoáng mát. Hệ Thống Bếp Thông Minh cao cấp.  \n" +
                "Kiến Trúc thiết kế hiện đại sang trọng ...',0)");

        db.execSQL("INSERT INTO NhaDat VALUES('21','Shop house kinh doanh trung tâm tp Bảo Lộc ',null,'Lâm Đồng','30/09/2021','Tản Đà, Xã Đạm Bri, TP. Bảo Lộc, Lâm Đồng',34000,'400m2','SUN VALLEY - KHU NGHỈ DƯỠNG ĐẲNG CẤP BẬC NHẤT BẢO LỘC. \n" +
                "Cách trung tâm TP Bảo Lộc 6km, khoảng 8 phút di chuyển. \n" +
                "Công viên cây xanh, hồ cảnh quan, khu resort quanh bờ suối',0)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS ThanhVien");
        db.execSQL("DROP TABLE IF EXISTS NhaDat");
        db.execSQL("DROP TABLE IF EXISTS Hinh");
        db.execSQL("DROP TABLE IF EXISTS donHang");
        onCreate(db);
    }
}
