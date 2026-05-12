package com.example.quanlysinhvien.Controller;

import com.example.quanlysinhvien.Model.LopHocPhan;
import com.example.quanlysinhvien.Service.ThoiKhoaBieuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
public class ThoiKhoaBieuController {

    private final ThoiKhoaBieuService thoiKhoaBieuService;

    public ThoiKhoaBieuController(ThoiKhoaBieuService thoiKhoaBieuService) {
        this.thoiKhoaBieuService = thoiKhoaBieuService;
    }

    @GetMapping("/sinhvien/lichhoc")
    public String thoiKhoaBieu(
            @RequestParam(defaultValue = "1") Integer hocKy,
            @RequestParam(defaultValue = "2024-2025") String namHoc,
            Model model) {

        Map<Integer, List<LopHocPhan>> lichHoc = thoiKhoaBieuService.getLichHoc(hocKy, namHoc);

        // Thứ hiện tại: Java DayOfWeek: MON=1..SUN=7
        // Hệ VN: Thứ 2=2 .. Thứ 7=7, CN=1
        int dow = LocalDate.now().getDayOfWeek().getValue(); // 1=Mon..7=Sun
        int ngayHienTai = (dow == 7) ? 1 : dow + 1; // Chuyển sang hệ VN

        model.addAttribute("lichHoc", lichHoc);
        model.addAttribute("ngayHienTai", ngayHienTai);
        model.addAttribute("hocKy", hocKy);
        model.addAttribute("namHoc", namHoc);

        return "Sinhvien/LichHoc";
    }

}