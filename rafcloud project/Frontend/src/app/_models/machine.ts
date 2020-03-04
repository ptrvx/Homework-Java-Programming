export class Machine {
    id: number;
    name: string;
    uid: string;
    status: string;
    active: boolean;
    datetime: Date;
    processing: boolean;

    constructor(id, name, uid, status, active, datetime) {
        this.id = id;
        this.name = name;
        this.uid = uid;
        this.status = status;
        this.active = active;
        this.datetime = datetime;
        this.processing = false;
    }

}