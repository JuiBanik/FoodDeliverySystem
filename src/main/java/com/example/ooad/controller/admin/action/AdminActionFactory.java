package com.example.ooad.controller.admin.action;
import java.util.HashMap;
import java.util.Map;
public class AdminActionFactory {
    Map<String, AdminAction> adminActionMap;

    public AdminActionFactory() {
        this.adminActionMap = new HashMap<String, AdminAction>();
        adminActionMap.put("Add a new Menu", new AddMenuAction());
        adminActionMap.put("Add Item", new AddItemAction());
        adminActionMap.put("Edit Item", new EditItemAction());
        adminActionMap.put("Delete Item", new DeleteItemAction());
        adminActionMap.put("Default", new DefaultAction());
    }

    public AdminAction getAdminAction(String action) {
        if (adminActionMap.containsKey(action)) {
            return adminActionMap.get(action);
        }
        return adminActionMap.get("Default");
    }

}
