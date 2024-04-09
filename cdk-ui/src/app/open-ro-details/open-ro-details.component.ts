import { environment } from './../../environments/environment';
import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'clapp-open-ro-details',
  templateUrl: './open-ro-details.component.html',
  styleUrls: ['./open-ro-details.component.scss']
})
export class OpenRoDetailsComponent implements OnInit {

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
      .get(environment.apiUrl + "/api/getOpenServiceOrderDetail/"+this.roNum).subscribe((data: any) => {
        if (data) this.roDetails = data;

      });
  }
}
