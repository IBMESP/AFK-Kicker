# AFK-Kicker
AFK kicker spigot plugin
## Badges:
[![Latest Version](https://img.shields.io/badge/Latest%20Version-1.1.1-brightgreen)](https://github.com/IBMESP/AFK-Kicker/releases/latest)
![Spigot Downloads](https://img.shields.io/spiget/downloads/100525?label=Spigot%20Downloads)
![Spigot Rating](https://img.shields.io/spiget/rating/100525?label=Spigot%20Rating)
![](https://i.imgur.com/UYlfp6f.png)
![](https://i.imgur.com/Vcf9j9Y.png)
- This plugins kicks player when they are AFK, you decide the time limit and how often the plugin checks for AFK player

![](https://i.imgur.com/0bsEda6.png)
![](https://media2.giphy.com/media/rxuMPWn4IDZ8mhi7uA/giphy.gif?cid=790b761189b9fc33c0a4b380ed5092d0db320cc06820b6c3&rid=giphy.gif&ct=g)
![](https://i.imgur.com/VTp3Ead.png)
- Permission: afk.bypass → You won't be kicked

![](https://i.imgur.com/BugfF9i.png)
<details>
  <summary>Default config.yml</summary>

```YAML
#Time limit
minutesAFK: 15

#Checks if a player is AFK every x seconds and stores the data
#More seconds -> Less accuracy less lag
#Fewer seconds -> More accuracy more lag
#Find a balance, start trying with fewer seconds
secondsInterval: 1

# Messages
kickIn: "&eYou will be kicked on &6%seconds% &eseconds"
kicked: "&cYou have been kicked for being AFK"
```
</details>
