import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'clapp-sales',
  templateUrl: './sales.component.html',
  styleUrls: ['./sales.component.scss']
})
export class SalesComponent implements OnInit {
  @Input() dataSource: any[];
  @Input() soldDate;
  @Output() loadClosedRoAction : EventEmitter<any> = new EventEmitter();
  @Input() loading;
  constructor(
    private _httpClient: HttpClient,
    public _router: Router,
    private datePipe: DatePipe
  ) { }

  ngOnInit() {
  }

  changeDate(isBefore){
    let dateString = this.soldDate+'T00:00:00' 
    let newDate = new Date(dateString);

    if(isBefore)
    newDate.setDate( newDate.getDate() - 1 );
    else
    newDate.setDate( newDate.getDate() + 1 );

    this.soldDate = this.datePipe.transform(newDate, 'yyyy-MM-dd');

    this.loadClosedRoAction.emit(this.soldDate);
  }

}
