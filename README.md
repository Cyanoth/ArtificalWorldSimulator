Artificial World Simulator is a Java application that simulates an artificial world in which lifeforms are able to sense their environment and survive in it.

## The Simulation

In the application there are four implemented types of lifeforms which are able to sense for consumable objects in range in each of the four cardinal points. Each lifeform type is able to sense at different distances and some lifeforms only eat a meat type consumables or non-meat type consumables. A lifeform is unable to sense outside its range, past an obstacle or outside of the map.

If a consumable is within the sensing range, then the lifeform will traverse towards it. If no consumable is detected or an obstacle is in the path of the lifeform, then the life form will move in random direction and will continue to do so until a consumable is sensed or the lifeform’s energy is depleted. Once the lifeform hits the consumable object, the energy value of the consumable is added to the lifeform’s energy and the consumable will despawn.

Another entity in Artificial World Simulator is the Virus. The virus appears on the simulation map but does not move and a lifeform is unable to sense it. If a lifeform hits the virus, then the bug will become infected and its image will turn red for an amount of time. An infected bug loses the ability to sense for consumables but it can still move and consume.
The virus entity will despawn immediately but can respawn again during the simulation.


![Screenshot of the main window](main_window.png?raw=true "Screenshot of the main window")

![Image sources & licenses](sources.png?raw=true "Image sources & licenses")
