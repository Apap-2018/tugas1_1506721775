package com.apap.tugas1.controller;

import com.apap.tugas1.model.Jabatan;
import com.apap.tugas1.service.InstansiService;
import com.apap.tugas1.service.JabatanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class JabatanController {

    private final
    JabatanService jabatanService;

    private final
    InstansiService instansiService;

    @Autowired
    public JabatanController(JabatanService jabatanService, InstansiService instansiService) {
        this.jabatanService = jabatanService;
        this.instansiService = instansiService;
    }

    @RequestMapping("/")
    public String index(Model model) {

        model.addAttribute("jabatans", jabatanService.findAllJabatan());
        model.addAttribute("instansis", instansiService.findAll());
        model.addAttribute("title", "Sistem Informasi Kepegawaian");
        return "index";
    }

    @RequestMapping(value = "/jabatan/viewall", method = RequestMethod.GET)
    public String viewAllJabatan(Model model) {

        model.addAttribute("jabatans", jabatanService.findAllJabatan());
        model.addAttribute("title", "Jabatan - All");
        return "list-jabatan";

    }

    @RequestMapping(value = "/jabatan/view", method = RequestMethod.GET)
    public String viewAllJabatan(@RequestParam(value = "idJabatan", required = false) Long idJabatan, Model model) {

        model.addAttribute("jabatans", jabatanService.findByIdJabatan(idJabatan));
        model.addAttribute("title", "Jabatan");

        return "jabatan-detail";

    }

    @RequestMapping(value = "/jabatan/ubah", method = RequestMethod.GET)
    public String updateJabatan(@RequestParam(value = "idJabatan", required = false) Long idJabatan, Model model) {

        model.addAttribute("jabatan", jabatanService.findByIdJabatan(idJabatan));
        model.addAttribute("title", "Update Jabatan");

        return "update-jabatan";
    }

    @RequestMapping(value = "/jabatan/ubah", method = RequestMethod.POST)
    public String update(@RequestParam(value = "idJabatan", required = false) Long idJabatan, @ModelAttribute Jabatan jabatan, RedirectAttributes redirectAttributes) {

        jabatanService.saveOrUpdate(jabatan);

        redirectAttributes.addAttribute("idJabatan", idJabatan).addFlashAttribute("message", "success");
        return "redirect:/jabatan/view";

    }

    @RequestMapping(value = "/jabatan/tambah", method = RequestMethod.POST)
    public String add(@ModelAttribute Jabatan jabatan, RedirectAttributes redirectAttributes) {

        jabatanService.saveOrUpdate(jabatan);
        redirectAttributes.addFlashAttribute("message", "success");
        return "redirect:/jabatan/viewall";
    }

    @RequestMapping(value = "/jabatan/tambah", method = RequestMethod.GET)
    public String addJabatan(Model model) {

        model.addAttribute("title", "Tambah Jabatan");
        return "add-jabatan";
    }

    @RequestMapping(value = "/jabatan/hapus")
    public String deleteJabatan(@RequestParam(value = "idJabatan", required = false) Long idJabatan, RedirectAttributes redirectAttributes) {

        try {
            jabatanService.delete(idJabatan);
            redirectAttributes.addFlashAttribute("message", "success-delete");
            return "redirect:/jabatan/viewall";
        } catch (Exception exc) {
            redirectAttributes.addAttribute("idJabatan", idJabatan).addFlashAttribute("message", "error");
            return "redirect:/jabatan/view";
        }
    }
}
