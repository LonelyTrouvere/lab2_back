package uni.LonelyTrouvere.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uni.LonelyTrouvere.lab.dto.BrigadeDTO;
import uni.LonelyTrouvere.lab.service.BrigadeService;
import uni.LonelyTrouvere.lab.service.JsonParser;
import uni.LonelyTrouvere.lab.service.RoleUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/brigade")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173/")
public class BrigadeController {
    private final BrigadeService service;

    @PutMapping
    private String doPut(@RequestHeader("access-token") String token, @RequestBody BrigadeDTO brigadeDTO) throws Exception {
        if(!RoleUtil.validateAccess(RoleUtil.getRole(token), RoleUtil.getAllowedRoles(new String[]{RoleUtil.ADMIN, RoleUtil.DISPATCH}))){
            return "[]";
        }
        service.update(brigadeDTO);
        return JsonParser.toJsonObject(service.getAll());
    }

    @GetMapping
    private String doGet(@RequestHeader("access-token") String token, @RequestParam("field") String field, @RequestParam("value") String value) throws Exception {
        String role = RoleUtil.getRole(token);
        List<BrigadeDTO> brigadeDTOList = new ArrayList<>();
        if(RoleUtil.validateAccess(role, RoleUtil.getAllowedRoles(new String[]{RoleUtil.USER, RoleUtil.DISPATCH, RoleUtil.ADMIN}))) {
            switch (field) {
                case "name":
                    brigadeDTOList = service.getByName(value);
                    break;
                case "id":
                    Optional<BrigadeDTO> brigadeDTO = service.get(value);
                    brigadeDTO.ifPresent(brigadeDTOList::add);
                    break;
                case "all":
                    brigadeDTOList = service.getAll();
                    break;
                default:
                    if (RoleUtil.validateAccess(role, RoleUtil.getAllowedRoles(new String[]{RoleUtil.DISPATCH, RoleUtil.ADMIN}))) {
                        switch (field) {
                            case "delete":
                                service.delete(value);
                                brigadeDTOList = service.getAll();
                                break;
                            default:
                                return "[]";
                        }
                    } else {
                        return "[]";
                    }
            }
        }
        return JsonParser.toJsonObject(brigadeDTOList);
    }

    @PostMapping
    private String doPost(@RequestHeader("access-token") String token, @RequestBody BrigadeDTO brigadeDTO) throws Exception {
        if(!RoleUtil.validateAccess(RoleUtil.getRole(token), RoleUtil.getAllowedRoles(new String[]{RoleUtil.ADMIN, RoleUtil.DISPATCH}))){
            return "[]";
        }
        service.create(brigadeDTO);
        return JsonParser.toJsonObject(service.getAll());
    }
}
