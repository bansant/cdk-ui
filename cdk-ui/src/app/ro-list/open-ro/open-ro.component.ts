import { environment } from './../../../environments/environment';
import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'clapp-open-ro',
  templateUrl: './open-ro.component.html',
  styleUrls: ['./open-ro.component.scss']
})
export class OpenRoComponent implements OnInit {

  @Input() dataSource: any[];
  @Input() loading;
  constructor(
    private _httpClient: HttpClient,
    public _router: Router,
    private _datePipe: DatePipe
  ) {}

  ngOnInit() {
 
  }
  gotoDetails(roNo) {
    this._router.navigate(["openrodetails/" + roNo]);
  }

}
