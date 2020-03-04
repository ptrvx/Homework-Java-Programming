export class Machine {
    private id: number;
    private name: string;
    private uid: string;
    private status: string;
    private active: boolean;
    private datetime: Date;

    constructor(id, name, uid, status, active, datetime) {
        this.id =id;
        this.name = name;
        this.uid = uid;
        this.status = status;
        this.active = active;
        this.datetime = datetime;
    }

}