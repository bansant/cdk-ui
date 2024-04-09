import { environment } from './../../../environments/environment';
import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, Inject, Input, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';


@Component({
  selector: 'clapp-ro-detail-popover',
  templateUrl: './ro-detail-popover.component.html',
  styleUrls: ['./ro-detail-popover.component.scss']
})
export class RoDetailPopoverComponent implements OnInit {

  @Input() roNum: any;
  @Input() closed: any;
  roDetails: any;
  loadingRODetails: boolean;
  
  constructor( 
    private _mdr: MatDialogRef<RoDetailPopoverComponent>,
    @Inject(MAT_DIALOG_DATA) data: any,
    private _httpClient: HttpClient,private _datePipe :DatePipe) { 
      this.roNum = data.rowNum;
      this.closed = data.closed;
      
    }

  ngOnInit() {
   
    console.log(this.roNum);
    if(this.closed) this.loadClosedRoDetails();
    else this.loadRoDetails();
  }

  loadRoDetails() {
    this.loadingRODetails = true;
    this.roDetails = [];
    this._httpClient
      .get(environment.apiUrl + "/api/getOpenServiceOrderDetail/"+this.roNum).subscribe((data: any) => {
        if (data) this.roDetails = data;
        this.loadingRODetails = false;
      });
  }

  loadClosedRoDetails() {
    this.loadingRODetails = true;
    this.roDetails = [];
    this._httpClient
      .get(environment.apiUrl + "/api/getServiceOrderDetail/"+this.roNum).subscribe((data: any) => {
        if (data) this.roDetails = data;
        this.loadingRODetails = false;
      });
  }

  CloseDialog() {
    this._mdr.close(false)
  }

}
