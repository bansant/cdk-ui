import { HttpClient } from '@angular/common/http';
import { environment } from './../../environments/environment';
import { Component, OnInit, Input, Output, EventEmitter, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'clapp-audit-trail',
  templateUrl: './audit-trail.component.html',
  styleUrls: ['./audit-trail.component.scss']
})
export class AuditTrailComponent implements OnInit {

 rowNum;
  auditTrial: any;
  loadingAuditTrial: boolean;
  
  constructor(
    private _httpClient: HttpClient,
    private _mdr: MatDialogRef<AuditTrailComponent>,
    @Inject(MAT_DIALOG_DATA) data: any
  ) {
    this.rowNum = data.rowNum;
  }

  ngOnInit() {
    console.log(this.rowNum);
    this.loadAuditTrial();
    
  }

  loadAuditTrial() {
    this.auditTrial= [];
    this.loadingAuditTrial = true;
 
    this._httpClient
      .get(environment.apiUrl + "/api/getAuditTrial/"+this.rowNum)
      .subscribe((data: any) => {
        if (data && data.length > 0) this.auditTrial = data;
        this.loadingAuditTrial = false;
      });
  }
  CloseDialog() {
    this._mdr.close(false)
  }

  getStatus(code){
    switch (code) {
      case 'I91':
        return 'Checked In'+'('+code+')';
      case 'I93':
        return 'Preassigned'+'('+code+')';
        case 'I98':
        return 'Working'+'('+code+')';
        case 'H98':
        return 'Vehicle Disabled';
        case 'C97':
        return 'Pre-Invoiced'+'('+code+')';
        case 'C94':
        return 'Ready To Post'+'('+code+')';
    }
  }

}
