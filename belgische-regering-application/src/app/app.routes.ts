import { Routes } from '@angular/router';
import { PartijListComponent } from './partij-list/partij-list.component';
import { HomeComponent } from './home/home.component';
import { RegeringListComponent } from './regering-list/regering-list.component';
import { PartijlidListComponent } from './partijlid-list/partijlid-list.component';

export const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'partijen', component: PartijListComponent },
    { path: 'regeringen', component: RegeringListComponent },
    { path: 'partijleden', component: PartijlidListComponent },
];

