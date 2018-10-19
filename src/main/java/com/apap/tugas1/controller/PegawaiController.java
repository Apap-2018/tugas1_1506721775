package com.apap.tugas1.controller;

import com.apap.tugas1.model.Instansi;
import com.apap.tugas1.model.JabatanPegawai;
import com.apap.tugas1.model.Pegawai;
import com.apap.tugas1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PegawaiController {

    private final
    PegawaiService pegawaiService;

    private final
    JabatanService jabatanService;

    private final
    InstansiService instansiService;

    private final
    ProvinsiService provinsiService;

    private final
    JabatanPegawaiService jabatanPegawaiService;


    @Autowired
    public PegawaiController(PegawaiService pegawaiService, JabatanService jabatanService, InstansiService instansiService, ProvinsiService provinsiService, JabatanPegawaiService jabatanPegawaiService) {

        this.pegawaiService = pegawaiService;
        this.jabatanService = jabatanService;
        this.instansiService = instansiService;
        this.provinsiService = provinsiService;
        this.jabatanPegawaiService = jabatanPegawaiService;
    }

    @RequestMapping(value = "/pegawai", method = RequestMethod.GET)
    public String viewPegawaiByNip(@RequestParam(value = "nip", required = false) String nip, Model model,
                                   RedirectAttributes redirectAttributes) {
        Pegawai pegawai = pegawaiService.findPegawaiByNip(nip);

        if (pegawai != null) {
            model.addAttribute("pegawai", pegawai);
            model.addAttribute("title", "Detail Pegawai");
            return "pegawai";
        }

        model.addAttribute("title", "NIP tidak ditemukan");
        redirectAttributes.addFlashAttribute("message", "error");
        redirectAttributes.addFlashAttribute("errmsg", "Nomor Induk Pegawai tidak dapat ditemukan");
        return "redirect:/";
    }

    @RequestMapping(value = "/pegawai/tambah", method = RequestMethod.GET)
    public String addPegawai(Model model) {
        ArrayList<JabatanPegawai> jabatanPegawais = new ArrayList<>();
        jabatanPegawais.add(new JabatanPegawai());

        Pegawai pegawai = new Pegawai();
        pegawai.setListJabatanPegawai(jabatanPegawais);

        model.addAttribute("pegawai", pegawai);
        model.addAttribute("listOfProvinsi", provinsiService.findAll());
        model.addAttribute("listOfJabatan", jabatanService.findAllJabatan());
        return "add-pegawai";
    }

    @RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST)
    public String addPegawaiSubmit(@ModelAttribute Pegawai pegawai, Model model,
                                   RedirectAttributes redirectAttributes) {

        Pegawai pegawaiBaru = pegawaiService.savePegawai(pegawai);

        for (JabatanPegawai jabatanPegawai : pegawai.getListJabatanPegawai()) {
            jabatanPegawai.setPegawai(pegawaiBaru);
            jabatanPegawaiService.addJabatanPegawai(jabatanPegawai);
        }
        model.addAttribute("pegawai", pegawai);
        redirectAttributes.addAttribute("nip", pegawaiBaru.getNip()).addFlashAttribute("messageCreated", "success");
        return "redirect:/pegawai";
    }

    @RequestMapping(value = "/pegawai/ubah", method = RequestMethod.GET)
    public String updatePegawai(@RequestParam(value = "pegawaiNip") String pegawaiNip, Model model) {
        model.addAttribute("instansis", instansiService.findAll());
        model.addAttribute("provinsis", provinsiService.findAll());
        model.addAttribute("jabatans", jabatanService.findAllJabatan());
        model.addAttribute("pegawai", pegawaiService.findPegawaiByNip(pegawaiNip));
        return "update-pegawai";
    }

    @RequestMapping(value = "/pegawai/ubah", method = RequestMethod.POST)
    public String updatePegawaiSubmit(@RequestParam(value = "idPegawai") Long idPegawai, Model model,
                                      @ModelAttribute Pegawai pegawai,
                                      RedirectAttributes redirectAttributes) {
        pegawai.setId(idPegawai);
        jabatanPegawaiService.deleteByPegawai(pegawai);
        for (JabatanPegawai jabatanPegawai : pegawai.getListJabatanPegawai()) {
            jabatanPegawai.setPegawai(pegawai);
            jabatanPegawaiService.addJabatanPegawai(jabatanPegawai);
        }
        String nip = pegawaiService.updatePegawai(pegawai);
        model.addAttribute("pegawai", pegawai);
        redirectAttributes.addAttribute("nip", nip).addFlashAttribute("message", "success");
        return "redirect:/pegawai";
    }

    @RequestMapping(value = "/pegawai/instansi", method = RequestMethod.GET)
    @ResponseBody
    public List<Instansi> findAllInstansi(@RequestParam(value = "idProvinsi") Integer idProvinsi) {
        return provinsiService.findById(idProvinsi).getInstansi();
    }

    @RequestMapping(value = "/pegawai-all", method = RequestMethod.GET)
    @ResponseBody
    public List<Pegawai> viewAll() {
        return pegawaiService.findAllPegawai();
    }

    @RequestMapping(value = "/pegawai/cari", method = RequestMethod.GET)
    public String advanceSearch(@RequestParam(value = "idProvinsi", required = false) Integer id_provinsi,
                                @RequestParam(value = "idInstansi", required = false) Long id_instansi,
                                @RequestParam(value = "idJabatan", required = false) Long id_jabatan,
                                Model model) {

        model.addAttribute("jabatans", jabatanService.findAllJabatan());
        model.addAttribute("instansis", instansiService.findAll());
        model.addAttribute("provinsis", provinsiService.findAll());
        model.addAttribute("id_provinsi", id_provinsi);
        model.addAttribute("id_instansi", id_instansi);
        model.addAttribute("id_jabatan", id_jabatan);

        StringBuilder result = new StringBuilder();
        if (id_instansi != null || id_jabatan != null || id_provinsi != null) {
            for (Object[] obj : pegawaiService.advanceSearch(id_instansi, id_provinsi, id_jabatan)) {
                result.append("<tr>");
                result.append("<td><a href='/pegawai?nip=").append(obj[0]).append("'>").append(obj[1]).append("</a></td>");
                result.append("<td>").append(obj[0]).append("</td>");
                result.append("<td>").append(obj[2]).append("</td>");
                String tanggal = obj[3].toString().substring(0, 10);
                result.append("<td>").append(tanggal).append("</td>");
                result.append("<td>").append(obj[4]).append("</td>");
                result.append("<td>").append(obj[5]).append(" - ").append(obj[7]).append("</td>");
                result.append("<td>").append(obj[6]).append("</td>");
                result.append("</tr>");
            }
        }
        model.addAttribute("listOfPegawai", result.toString());
        model.addAttribute("title", "Cari Pegawai");
        return "cari-pegawai";
    }

    @RequestMapping(value = "/pegawai/termuda-tertua", method = RequestMethod.GET)
    public String pegawiTertuaTermuda(@RequestParam(value = "idInstansi") Long id, Model model) {

        //Tertua
        Pegawai pegawaiTertua = pegawaiService.findFirstByInstansi_IdOrderByTanggalLahirAsc(id);
        model.addAttribute("pegawaiTertua", pegawaiTertua);

        //Termuda
        Pegawai pegawaiTermuda = pegawaiService.findFirstByInstansi_IdOrderByTanggalLahirDesc(id);
        model.addAttribute("pegawaiTermuda", pegawaiTermuda);

        return "pegawai-tertua-termuda";
    }
}
