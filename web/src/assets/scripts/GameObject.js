const GAME_OBJECTS = [];

export class GameObject {
    constructor() {
        GAME_OBJECTS.push(this);
        this.timedelta = 0;


    }

    start() {

    }

    update() {

    }
    on_destroy() {

    }

    destroy(){
        this.on_destroy();

        for(let i in GAME_OBJECTS){
            const obj =GAME_OBJECTS[i];
            if (obj === this) {
                GAME_OBJECTS.splice(i);
                break;
            }
        }
    }

}

let last_timestep;

const step = timestamp => {
    for (let obj of GAME_OBJECTS) {
        if(!obj.has_called_start){
            obj.has_called_start = true;
            obj.start();
        } else {
            obj.timedelta = timestamp - last_timestep;
            obj.update();
        }
    }
    last_timestep = timestamp;
    requestAnimationFrame(step);
}



requestAnimationFrame(step);