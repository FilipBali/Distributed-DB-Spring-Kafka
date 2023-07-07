package com.pdb_db.pdb_proj.domain.costume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/costume")
public class CostumeController
{
    private final CostumeService costumeService;

    @Autowired
    public CostumeController(CostumeService costumeService) {
        this.costumeService = costumeService;
    }

    @GetMapping
    public List<Costume> getCostumes()
    {
        return costumeService.getCostumes();
    }

    @PostMapping
    public void registerNewCostume(@RequestBody Costume costume)
    {
        costumeService.addNewCostume(costume);
    }

    @DeleteMapping(path = "{id}")
    public void deleteCostume(@PathVariable("id") Integer id)
    {
        costumeService.deleteCostume(id);
    }

    @PutMapping(path = "{id}")
    public void updateDoplnok(
            @PathVariable("id") Integer id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String material,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Integer size_number,
            @RequestParam(required = false) java.util.Date production_date)
    {
        costumeService.updateCostume(id, name, description,material, category, size_number, production_date);
    }
}
