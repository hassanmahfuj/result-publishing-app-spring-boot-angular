import { Subject } from './subject';

export interface Result {
  resultId: number;
  rollNumber: number;
  year: number;
  groupName: string;
  examination: string;
  studentId: number;
  subjects: Subject[];
}
