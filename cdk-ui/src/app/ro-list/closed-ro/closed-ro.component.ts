import { environment } from "./../../../environments/environment";
import { DatePipe } from "@angular/common";
import { HttpClient } from "@angular/common/http";
import { Component, EventEmitter, Input, OnInit, Output } from "@angular/core";
import { Router } from "@angular/router";


@Component({
  selector: "clapp-closed-ro",
  templateUrl: "./closed-ro.component.html",
  styleUrls: ["./closed-ro.component.scss"],
})
export class ClosedRoComponent implements OnInit {
  @Input() dataSource: any[];
  @Input() closedDate;
  @Output() loadClosedRoAction : EventEmitter<any> = new EventEmitter();
  @Input() loading;
  constructor(
    private _httpClient: HttpClient,
    public _router: Router,
    private datePipe: DatePipe
  ) {}

  ngOnInit() {
  }
  

  gotoDetails(roNo) {
    this._router.navigate(["closedrodetails/" + roNo]);
  }
  

  changeDate(isBefore){
    let dateString = this.closedDate+'T00:00:00' 
    let newDate = new Date(dateString);

    if(isBefore)
    newDate.setDate( newDate.getDate() - 1 );
    else
    newDate.setDate( newDate.getDate() + 1 );

    this.closedDate = this.datePipe.transform(newDate, 'yyyy-MM-dd');

    this.loadClosedRoAction.emit(this.closedDate);
  }
}
