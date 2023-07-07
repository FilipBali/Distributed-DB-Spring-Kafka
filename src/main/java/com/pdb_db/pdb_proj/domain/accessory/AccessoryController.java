package com.pdb_db.pdb_proj.domain.accessory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/accessory")
public class AccessoryController
{
    private final AccessoryService accessoryService;

    @Autowired
    public AccessoryController(AccessoryService accessoryService) {
        this.accessoryService = accessoryService;
    }

    @GetMapping
    public List<Accessory> getAccessories()
    {
        return accessoryService.getAccessories();
    }

    @PostMapping
    public void registerNewAccessory(@RequestBody Accessory accessory)
    {
        accessoryService.addNewAccessory(accessory);
    }

    @DeleteMapping(path = "{id}")
    public void deleteAccessory(@PathVariable("id") Integer accessoryId)
    {
        accessoryService.deleteAccessory(accessoryId);
    }

    @PutMapping(path = "{id}")
    public void updateAccessory(
            @PathVariable("id") Integer accessoryId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String material,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) java.util.Date production_date)
    {
        accessoryService.updateAccessory(accessoryId, name, description,material, category, production_date);
    }
}
