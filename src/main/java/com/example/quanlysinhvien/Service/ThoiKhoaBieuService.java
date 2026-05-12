package com.example.quanlysinhvien.Service;

import com.example.quanlysinhvien.Model.KetQuaHocTap;
import com.example.quanlysinhvien.Model.LopHocPhan;
import com.example.quanlysinhvien.Model.SinhVien;
import com.example.quanlysinhvien.repository.KetQuaHocTapRepository;
import com.example.quanlysinhvien.repository.LopHocPhanRepository;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ThoiKhoaBieuService {

    private final LopHocPhanRepository lopHocPhanRepository;

    public ThoiKhoaBieuService(LopHocPhanRepository lopHocPhanRepository) {
        this.lopHocPhanRepository = lopHocPhanRepository;
    }

    public Map<Integer, List<LopHocPhan>> getLichHoc(Integer hocKy, String namHoc) {
        // 1. Khởi tạo Map rỗng chứa các Thứ từ 2 đến 7
        Map<Integer, List<LopHocPhan>> lichHoc = new HashMap<>();
        for (int i = 2; i <= 7; i++) {
            lichHoc.put(i, new ArrayList<>());
        }

        // 2. Kéo dữ liệu từ DB lên
        List<LopHocPhan> danhSachLop = lopHocPhanRepository.findByHocKyAndNamHoc(hocKy, namHoc);

        // 3. Phân loại từng môn học vào đúng Thứ
        for (LopHocPhan lop : danhSachLop) {
            Integer thu = lop.getThuTrongTuan();

            // Đảm bảo dữ liệu DB hợp lệ (từ Thứ 2 đến Thứ 7)
            if (thu != null && thu >= 2 && thu <= 7) {
                lichHoc.get(thu).add(lop);
            }
        }

        return lichHoc;
    }
}