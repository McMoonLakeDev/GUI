# MoonLakeGUI
Minecraft MoonLake GUI Plugin
By Month_Light
## 简介
这个插件提供了创建 GUI 以及设置按钮交互操作<br />
极其简单的 GUI 操作方法，为您呈现用户图形化界面。
## 未来计划
* 这是我们未来即将开发的 API 功能
* 1. 更多按钮的封装
* 2. 更多 GUI 的类型封装
* 3. 添加对 MySQL 的支持

## 使用方法
注意将您的插件内 `plugin.yml` 添加 `depend: [MoonLakeGUI]` 前置支持
```java
private MoonLakeGUIManager manager;

/**
 * 加载月色之湖前置 GUI 插件
 *
 * @return 是否加载成功
 */
private boolean setupMoonLakeGUI() {

  Plugin plugin = this.getServer().getPluginManager().getPlugin("MoonLakeGUI");
  return plugin != null && plugin instanceof GUIPlugin && (this.manager = ((GUIPlugin)plugin).getManager()) != null;
}
```
调用的话就在主类的 `onEnable` 函数里面
```java
@Override
public void onEnable() {

  if(!setupMoonLakeGUI()) {
    // 前置插件 MoonLakeGUI 加载失败
    return;
  }
  // 前置插件 MoonLakeGUI 加载成功
}
```
新建一个 GUI 对象并添加按钮行为以及注册
```java
GUI gui = manager.createGUI("mygui", "这是我创建的GUI", 6);
gui.addButton(new ItemStack(Material.DIAMOND), new GUIButtonExecute() {

  @Override
  public void execute(GUI gui, Player clicked, GUIButton currentButton) {
    
    clicked.closeInventory();
    clicked.sendMessage("你竟然点了一下我创建的GUI 23333");
  }
});

// 记着一定要注册你创建的 GUI 对象
manager.registerGUI(gui);

// 给一个玩家打开此 GUI 对象
gui.open(player);
```
## 其他插件
* `MoonLake` 核心 API 插件 :point_right:[GO](http://github.com/u2g/MoonLake "MoonLake Plugin")
* `MoonLakeKitPvP` 职业战争插件 :point_right:[GO](http://github.com/u2g/MoonLakeKitPvP "MoonLake KitPvP Plugin")
* `MoonLakeSkinme` 玩家皮肤披风操作插件 :point_right:[GO](http://github.com/u2g/MoonLakeSkinme "MoonLake Skinme Plugin")
* `MoonLakeEconomy` 基于 `MySQL` 的经济插件 :point_right:[GO](http://github.com/u2g/MoonLakeEconomy "MoonLake Economy Plugin")

Website: [MoonLake](http://www.mcyszh.com "MoonLake Website")<br />
Minecraft MoonLake Core API Plugin
By Month_Light