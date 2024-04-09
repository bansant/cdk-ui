import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'clapp-rodetails',
  templateUrl: './rodetails.component.html',
  styleUrls: ['./rodetails.component.scss']
})
export class RodetailsComponent implements OnInit {
  roNum: any;
  roDetails: any;

  constructor( private _activatedRoute: ActivatedRoute,
    private _httpClient: HttpClient,private _datePipe :DatePipe) { }

  ngOnInit() {
    this.roNum = this._activatedRoute.snapshot.params.id;
    console.log(this.roNum);
    this.loadRoDetails();
  }

  loadRoDetails() {

    this._httpClient
      .get(environment.apiUrl + "/api/getServiceOrderDetail/"+this.roNum).subscribe((data: any) => {
        if (data) this.roDetails = data;

      });
  }
}
