import { Date } from "./date";

export class Match {
    homeTeamName: string;
    awayTeamName: string;
    hgoalsScored: number;
    agoalsScored: number;
    date: Date;
    stdate: string;

    constructor(
        homeTeamName: string,
        awayTeamName: string,
        hgoalsScored: number,
        agoalsScored: number,
        date: Date,
        stdate: string,
    ){}
}
