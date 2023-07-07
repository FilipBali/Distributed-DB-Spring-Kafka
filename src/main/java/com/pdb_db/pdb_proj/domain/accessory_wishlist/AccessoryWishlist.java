package com.pdb_db.pdb_proj.domain.accessory_wishlist;

import com.pdb_db.pdb_proj.utilities.rest_operationType;

import javax.persistence.*;

@Entity
@Table
public class AccessoryWishlist implements AccessoryWishlistInterface {

    @Id
    @SequenceGenerator(
            name = "accessorywishlist_sequence",
            sequenceName = "accessorywishlist_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "accessorywishlist_sequence"
    )
    private Integer id;
    private String name;
    private Integer customerId;
    private Integer accessoryId;

    // CRUD operation
    @Transient
    private rest_operationType rest_operation;

    public AccessoryWishlist() {}
    public AccessoryWishlist(Integer id) {
        this.id = id;
    }

    public AccessoryWishlist(String name, Integer customerId, Integer accessoryId) {
        this.name = name;
        this.customerId = customerId;
        this.accessoryId = accessoryId;
    }

    public AccessoryWishlist(Integer id, String name, Integer customerId, Integer accessoryId) {
        this.id = id;
        this.name = name;
        this.customerId = customerId;
        this.accessoryId = accessoryId;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id)
    {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }


    @Override
    public Integer getCustomerId() {
        return customerId;
    }
    @Override
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }


    @Override
    public Integer getAccessoryId() {
        return accessoryId;
    }

    public void setAccessoryId(Integer accessoryId)
    {
        this.accessoryId = accessoryId;
    }

    public rest_operationType getOperation(){ return rest_operation; }
    public void setOperation(rest_operationType rest_operation){ this.rest_operation = rest_operation; }
}
