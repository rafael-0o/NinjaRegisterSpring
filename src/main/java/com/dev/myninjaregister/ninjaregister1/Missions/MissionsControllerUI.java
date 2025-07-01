package com.dev.myninjaregister.ninjaregister1.Missions;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/api/missions/ui")
public class MissionsControllerUI {
   private MissionsService missionsService;
    public MissionsControllerUI(MissionsService missionsService) {
        this.missionsService = missionsService;
    }

    @GetMapping("/create")
    public String createMission(Model model) {
      model.addAttribute("mission", new MissionsDTO());
        return "missions/createmission";
    }

    @PostMapping("/save")
    public String saveMission(@ModelAttribute MissionsDTO mission, RedirectAttributes redirectAttributes){
        missionsService.createNewMission(mission);
        redirectAttributes.addFlashAttribute("Mensage", "Mission created");
        return "redirect:/api/missions/ui/showall";
    }

    @GetMapping("/showall")
    public String showAllMissions(Model model) {
        List<MissionsDTO> missionsList = missionsService.showAllMissions();
        model.addAttribute("missions", missionsList);
        return "missions/missionslist";
    }

    @GetMapping("/showbyid/{id}")
    public String showMissionById(@PathVariable Long id, Model model) {
        MissionsDTO mission = missionsService.showMissionsById(id);
        if (mission!=null){
            model.addAttribute("missions",mission);
            return "missions/mdetails";
        } else {
            model.addAttribute("Mensage","Mission not found");
            return"missions/missionlist";
        }
    }

    @GetMapping("/editbyid/{id}")
    public String updateMission(@PathVariable Long id, Model model) {
        model.addAttribute("mission", missionsService.showMissionsById(id));
        return "missions/updatemission";
    }

    @PutMapping("/saveupdate/{id}")
    public String saveUpdate(@PathVariable Long id, @ModelAttribute MissionsDTO missions, RedirectAttributes redirectAttributes){
        missionsService.modifyMission(id, missions);
        redirectAttributes.addFlashAttribute("Update","Mission Updated");
        return "redirect:/api/missions/ui/showall";
    }

    @DeleteMapping("/deletebyid/{id}")
    public String deleteMission(@PathVariable Long id) {
        missionsService.deleteMissionById(id);
        return "redirect:/api/missions/ui/showall";
    }
}
